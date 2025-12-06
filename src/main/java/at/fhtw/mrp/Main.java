package at.fhtw.mrp;

import at.fhtw.mrp.model.UserRepository;
import at.fhtw.mrp.server.RegisterHandler;
import at.fhtw.mrp.server.Router;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        System.out.println("Starting server on port " + port + "...");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Router router = new Router();

        // In-Memory User Storage
        UserRepository userRepo = new UserRepository();

        // /register Endpoint
        router.registerRoute("/register", new RegisterHandler(userRepo));

        // /ping Endpoint
        router.registerRoute("/ping", exchange -> {
            String response = "pong";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        router.attachRoutesToServer(server);

        server.setExecutor(null);
        server.start();

        System.out.println("Server ready at http://localhost:" + port + "/ping");
        System.out.println("Register endpoint at http://localhost:" + port + "/register");
    }
}

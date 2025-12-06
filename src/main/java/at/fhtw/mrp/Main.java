package at.fhtw.mrp;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting server on port 8080...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/ping", new PingHandler());

        server.setExecutor(null); // default executor
        server.start();

        System.out.println("Server is running at: http://localhost:8080/ping");
    }

    static class PingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "pong";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}

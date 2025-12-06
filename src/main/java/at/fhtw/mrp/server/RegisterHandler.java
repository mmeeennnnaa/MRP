package at.fhtw.mrp.server;

import at.fhtw.mrp.model.User;
import at.fhtw.mrp.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RegisterHandler implements RequestHandler {

    private final UserRepository userRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public RegisterHandler(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        InputStream is = exchange.getRequestBody();
        var body = mapper.readTree(is);

        String username = body.get("username").asText();
        String password = body.get("password").asText();

        if (userRepo.findByUsername(username) != null) {
            String msg = "User already exists";
            exchange.sendResponseHeaders(409, msg.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(msg.getBytes());
            }
            return;
        }

        User user = new User(username, password);
        userRepo.addUser(user);

        String response = "User registered";
        exchange.sendResponseHeaders(201, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}

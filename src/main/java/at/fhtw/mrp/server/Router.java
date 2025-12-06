package at.fhtw.mrp.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, RequestHandler> routes = new HashMap<>();

    public void registerRoute(String path, RequestHandler handler) {
        routes.put(path, handler);
    }

    public void attachRoutesToServer(HttpServer server) {
        for (var entry : routes.entrySet()) {
            server.createContext(entry.getKey(), exchange -> {
                try {
                    entry.getValue().handle(exchange);
                } catch (Exception e) {
                    e.printStackTrace(); // zu Debug-Zwecken
                    sendError(exchange, 500, "Internal server error: " + e.getMessage());
                }
            });
        }
    }

    private void sendError(HttpExchange exchange, int status, String message) {
        try {
            exchange.sendResponseHeaders(status, message.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(message.getBytes());
            }
        } catch (Exception ignored) {
            // let it fail silently
        }
    }
}

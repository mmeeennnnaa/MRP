package at.fhtw.mrp.server;

import com.sun.net.httpserver.HttpExchange;

public interface RequestHandler {
    void handle(HttpExchange exchange) throws Exception;
}

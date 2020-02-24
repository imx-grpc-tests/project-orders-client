
package com.max.grpc.orders.client;

import com.max.grpc.orders.client.rest.RestServer;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    private static final int GRPC_CLIENT_PORT = 8000;
    private static final int REST_SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        var client = new CafeClient("localhost", GRPC_CLIENT_PORT);
        var server = new RestServer(REST_SERVER_PORT, client);
        server.start();
        logger.info("Hit enter to stop it...");
        System.in.read();
        server.stop();
    }
}

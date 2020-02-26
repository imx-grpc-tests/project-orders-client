
package com.max.grpc.orders.client;

import com.max.grpc.orders.client.rest.RestServer;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    private static final int DEFAULT_GRPC_SERVER_PORT = 8000;
    private static final int DEFAULT_REST_SERVER_PORT = 8080;
    private static final String CONFIG_PATH = "src/main/resources/server.properties";

    public static void main(String[] args) throws IOException {
        var config = new ConfigLoader(CONFIG_PATH);
        config.load();
        int grpcServerPort = config.getGrpcServerPortOrDefault(DEFAULT_GRPC_SERVER_PORT);
        int restServerPort = config.getRestServerPortOrDefault(DEFAULT_REST_SERVER_PORT);

        var client = new CafeClient("localhost", grpcServerPort);
        var server = new RestServer(restServerPort, client);
        server.start();

        logger.info("Hit enter to stop it...");
        System.in.read();
        server.stop();
    }
}

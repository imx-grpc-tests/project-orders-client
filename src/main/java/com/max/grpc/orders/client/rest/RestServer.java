
package com.max.grpc.orders.client.rest;

import com.max.grpc.orders.client.CafeClient;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class RestServer {
    private int port;
    private HttpServer server;
    private CafeClient cafeClient;
    private final URI BASE_URI;
    private final Logger logger = Logger.getLogger(RestServer.class);

    public RestServer(int port, CafeClient cafeClient) {
        this.port = port;
        this.server = null;
        this.cafeClient = cafeClient;
        this.BASE_URI = getBaseURI();
    }

    public void start() {
        if (server == null || !server.isStarted()) {
            var resConf = ResourceConfig.forApplication(new RestApplication(cafeClient));
            this.server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resConf);
            logger.info("Server started, listening on " + BASE_URI);
        } else {
            logger.info("Server is already started");
        }
    }

    public void stop() {
        if (server != null && server.isStarted()) {
            server.shutdownNow();
            logger.info("Server stopped");
        } else {
            logger.info("Server is already stopped or hasn't started yet");
        }
    }

    private URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(port).build();
    }
}

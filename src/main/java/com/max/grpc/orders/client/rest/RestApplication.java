
package com.max.grpc.orders.client.rest;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.services.MenuServiceImpl;
import com.max.grpc.orders.client.rest.services.OrderServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("services")
public class RestApplication extends Application {
    private CafeClient cafeClient;

    public RestApplication(CafeClient cafeClient) {
        this.cafeClient = cafeClient;
    }

    @Override
    public Set<Object> getSingletons() {
        var services = new HashSet<>();
        services.add(new MenuServiceImpl(cafeClient));
        services.add(new OrderServiceImpl(cafeClient));
        return services;
    }
}

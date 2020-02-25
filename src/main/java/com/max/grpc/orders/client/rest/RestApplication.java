
package com.max.grpc.orders.client.rest;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.mappers.FoodItemMapperImpl;
import com.max.grpc.orders.client.rest.services.MenuServiceImpl;
import com.max.grpc.orders.client.rest.services.OrderServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("services")
public class RestApplication extends Application {
    private CafeClient cafeClient;
    private FoodItemMapperImpl foodItemMapper;

    public RestApplication(CafeClient cafeClient) {
        this.cafeClient = cafeClient;
        this.foodItemMapper = new FoodItemMapperImpl();
    }

    @Override
    public Set<Object> getSingletons() {
        var services = new HashSet<>();
        services.add(new MenuServiceImpl(cafeClient, foodItemMapper));
        services.add(new OrderServiceImpl(cafeClient, foodItemMapper));
        return services;
    }
}

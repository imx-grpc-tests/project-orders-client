
package com.max.grpc.orders.client.rest;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.controllers.MenuController;
import com.max.grpc.orders.client.rest.controllers.OrdersController;
import com.max.grpc.orders.client.rest.mappers.CafeMenuMapperImpl;
import com.max.grpc.orders.client.rest.mappers.OrderReceiptMapperImpl;
import com.max.grpc.orders.client.rest.services.MenuService;
import com.max.grpc.orders.client.rest.services.OrdersService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("services")
public class RestApplication extends Application {
    private MenuService menuService;
    private OrdersService ordersService;

    public RestApplication(CafeClient cafeClient) {
        var cafeMenuMapper = new CafeMenuMapperImpl();
        var orderReceiptMapper = new OrderReceiptMapperImpl();

        this.menuService = new MenuService(cafeClient, cafeMenuMapper);
        this.ordersService = new OrdersService(cafeClient, orderReceiptMapper);
    }

    @Override
    public Set<Object> getSingletons() {
        var controllers = new HashSet<>();
        controllers.add(new MenuController(menuService));
        controllers.add(new OrdersController(ordersService));
        return controllers;
    }
}

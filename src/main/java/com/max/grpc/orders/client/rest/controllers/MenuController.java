
package com.max.grpc.orders.client.rest.controllers;

import com.max.grpc.orders.client.rest.models.ApiCafeMenu;
import com.max.grpc.orders.client.rest.services.MenuService;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/menu")
public class MenuController {
    private MenuService menuService;
    private final Logger logger = Logger.getLogger(MenuController.class);

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiCafeMenu getMenu() {
        logger.info("Incoming getMenu request");
        ApiCafeMenu response = menuService.getMenu();
        logger.info("Response sent");
        return response;
    }
}

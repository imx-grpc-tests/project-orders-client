
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.mappers.CafeMenuMapperImpl;
import com.max.grpc.orders.client.rest.mappers.FoodItemMapperImpl;
import com.max.grpc.orders.client.rest.models.ApiCafeMenu;
import com.max.grpc.orders.proto.CafeMenu;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/menu")
public class MenuServiceImpl {
    private CafeClient cafeClient;
    private FoodItemMapperImpl foodItemMapper;
    private CafeMenuMapperImpl cafeMenuMapper;
    private final Logger logger = Logger.getLogger(MenuServiceImpl.class);

    public MenuServiceImpl(CafeClient cafeClient, FoodItemMapperImpl foodItemMapper) {
        this.cafeClient = cafeClient;
        this.foodItemMapper = foodItemMapper;
        this.cafeMenuMapper = new CafeMenuMapperImpl();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiCafeMenu getMenu() {
        logger.info("Getting menu from cafe...");
        CafeMenu cafeMenu = cafeClient.getMenu();
        logger.info("Received " + cafeMenu.getItemsCount() + " dishes");

        logger.info("Response sent");
        return cafeMenuMapper.protoToRestModel(cafeMenu);
    }
}


package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.models.ApiCafeMenu;
import com.max.grpc.orders.client.rest.models.ApiFoodItem;
import com.max.grpc.orders.proto.CafeMenu;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/menu")
public class MenuServiceImpl implements MenuService {
    private CafeClient cafeClient;
    private final Logger logger = Logger.getLogger(MenuServiceImpl.class);

    public MenuServiceImpl(CafeClient cafeClient) {
        this.cafeClient = cafeClient;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public ApiCafeMenu getMenu() {
        logger.info("Getting menu from cafe...");
        CafeMenu cafeMenu = cafeClient.getMenu();
        logger.info("Received " + cafeMenu.getItemsCount() + " dishes");

        ApiCafeMenu responseMenu = new ApiCafeMenu();
        List<ApiFoodItem> resFoodItems = cafeMenu.getItemsList().stream()
            .map(ApiFoodItem::from)
            .collect(Collectors.toList());
        responseMenu.setItems(resFoodItems);

        logger.info("Response sent");
        return responseMenu;
    }
}


package com.max.grpc.orders.client.rest.controllers;

import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.client.rest.services.OrdersService;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
public class OrdersController {
    private OrdersService ordersService;
    private final Logger logger = Logger.getLogger(OrdersController.class);

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiOrderReceipt makeOrder(List<String> itemIds) {
        logger.info("Incoming makeOrder request");
        ApiOrderReceipt receipt = ordersService.makeOrder(itemIds);
        logger.info("Response sent");
        return receipt;
    }
}

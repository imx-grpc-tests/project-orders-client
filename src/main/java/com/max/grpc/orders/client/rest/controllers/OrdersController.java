
package com.max.grpc.orders.client.rest.controllers;

import com.max.grpc.orders.client.grpc.ResponseObserver;
import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.client.rest.services.OrdersService;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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
        logger.info("make-order: incoming request");
        ApiOrderReceipt receipt = ordersService.makeOrder(itemIds);
        logger.info("make-order: response sent");
        return receipt;
    }

    @POST
    @ManagedAsync
    @Path("/new-async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void makeOrderAsync(List<String> itemIds, @Suspended AsyncResponse response) {
        logger.info("make-order-async: incoming request");
        ordersService.makeOrderAsync(itemIds, new ResponseObserver<>() {
            @Override
            public void onNext(ApiOrderReceipt receipt) {
                response.resume(receipt);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("make-order-async: error occurred", throwable);
                response.resume(throwable);
            }

            @Override
            public void onCompleted() {
                logger.info("make-order-async: response sent");
            }
        });
    }
}

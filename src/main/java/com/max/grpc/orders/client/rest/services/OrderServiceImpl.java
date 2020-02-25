
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.mappers.FoodItemMapperImpl;
import com.max.grpc.orders.client.rest.mappers.OrderReceiptMapperImpl;
import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.proto.OrderReceipt;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
public class OrderServiceImpl {
    private CafeClient cafeClient;
    private FoodItemMapperImpl foodItemMapper;
    private OrderReceiptMapperImpl orderReceiptMapper;
    private final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(CafeClient cafeClient, FoodItemMapperImpl foodItemMapper) {
        this.cafeClient = cafeClient;
        this.foodItemMapper = foodItemMapper;
        this.orderReceiptMapper = new OrderReceiptMapperImpl();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiOrderReceipt makeOrder(List<String> itemIds) {
        logger.info("Making order, " + itemIds.size() + " items");
        OrderReceipt receipt = cafeClient.makeOrder(itemIds);
        logger.info("Order made, receipt received");

        logger.info("Response sent");
        return orderReceiptMapper.protoToRestModel(receipt);
    }
}

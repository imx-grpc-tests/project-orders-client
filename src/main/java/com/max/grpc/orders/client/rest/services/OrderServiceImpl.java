
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.models.ApiFoodItem;
import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.proto.OrderReceipt;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/orders")
public class OrderServiceImpl {
    private CafeClient cafeClient;
    private final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(CafeClient cafeClient) {
        this.cafeClient = cafeClient;
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiOrderReceipt makeOrder(List<String> itemIds) {
        logger.info("Making order, " + itemIds.size() + " items");
        OrderReceipt receipt = cafeClient.makeOrder(itemIds);
        logger.info("Order made, receipt received");

        ApiOrderReceipt responseReceipt = new ApiOrderReceipt();
        responseReceipt.setId(receipt.getId());
        responseReceipt.setDate(receipt.getDate());
        List<ApiFoodItem> resItems = receipt.getItemsList().stream()
            .map(ApiFoodItem::from)
            .collect(Collectors.toList());
        responseReceipt.setItems(resItems);
        responseReceipt.setTotalPrice(receipt.getTotalPrice());

        logger.info("Response sent");
        return responseReceipt;
    }
}

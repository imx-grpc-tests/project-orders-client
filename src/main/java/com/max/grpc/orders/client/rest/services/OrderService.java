
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
public interface OrderService {
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ApiOrderReceipt makeOrder(List<String> itemIds);
}

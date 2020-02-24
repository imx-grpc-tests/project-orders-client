
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.rest.models.ApiCafeMenu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/menu")
public interface MenuService {
    @GET
    @Path("/")
    ApiCafeMenu getMenu();
}

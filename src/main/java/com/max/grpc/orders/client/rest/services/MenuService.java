
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.CafeClient;
import com.max.grpc.orders.client.rest.mappers.CafeMenuMapperImpl;
import com.max.grpc.orders.client.rest.models.ApiCafeMenu;
import com.max.grpc.orders.proto.CafeMenu;

import org.apache.log4j.Logger;

public class MenuService {
    private CafeClient cafeClient;
    private CafeMenuMapperImpl cafeMenuMapper;
    private final Logger logger = Logger.getLogger(MenuService.class);

    public MenuService(CafeClient cafeClient, CafeMenuMapperImpl cafeMenuMapper) {
        this.cafeClient = cafeClient;
        this.cafeMenuMapper = cafeMenuMapper;
    }

    public ApiCafeMenu getMenu() {
        logger.info("Getting menu from cafe...");
        CafeMenu cafeMenu = cafeClient.getMenu();
        logger.info("Received " + cafeMenu.getItemsCount() + " dishes");

        logger.info("Forming response...");
        return cafeMenuMapper.protoToRestModel(cafeMenu);
    }
}

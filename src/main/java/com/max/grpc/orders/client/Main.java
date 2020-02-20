
package com.max.grpc.orders.client;

import com.max.grpc.orders.proto.FoodItem;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        var client = new CafeClient("localhost", 8000);

        logger.info("Getting menu from cafe...");
        List<FoodItem> menuItems = client.getMenu().getItemsList();

        List<String> itemIds = menuItems.stream().map(FoodItem::getId).collect(Collectors.toList());
        logger.info("Ordering all items...");
        client.makeOrder(itemIds);

        logger.info("Response received, shutting down...");
        client.shutdown();
    }
}

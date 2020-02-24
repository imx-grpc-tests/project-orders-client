
package com.max.grpc.orders.client;

import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.OrderReceipt;

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

        OrderReceipt receipt = client.makeOrder(itemIds);
        if (receipt != null) {
            logger.info(LogUtils.printReceipt(receipt));
        } else {
            logger.error("Order receipt is empty");
        }

        logger.info("Shutting down...");
        client.shutdown();
    }
}

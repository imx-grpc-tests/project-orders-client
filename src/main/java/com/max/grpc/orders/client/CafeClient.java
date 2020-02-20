
package com.max.grpc.orders.client;

import com.google.protobuf.Empty;
import com.max.grpc.orders.proto.*;
import com.max.grpc.orders.proto.MenuServiceGrpc.MenuServiceBlockingStub;
import com.max.grpc.orders.proto.OrderServiceGrpc.OrderServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CafeClient {
    private Logger logger = Logger.getLogger(CafeClient.class);
    private ManagedChannel managedChannel;
    private MenuServiceBlockingStub menuStub;
    private OrderServiceBlockingStub orderStub;

    public CafeClient(String host, int port) {
        var channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.managedChannel = channel;

        this.menuStub = MenuServiceGrpc.newBlockingStub(channel);
        this.orderStub = OrderServiceGrpc.newBlockingStub(channel);
    }

    public CafeMenu getMenu() {
        logger.info("Retrieving cafe menu...");
        try {
            CafeMenu cafeMenu = menuStub.getMenu(Empty.getDefaultInstance());
            logger.info("Menu received");
            return cafeMenu;
        }
        catch (StatusRuntimeException ex) {
            logger.error("Cannot get cafe menu", ex);
            return null;
        }
    }

    public void makeOrder(List<String> items) {
        logger.info("Sending order request...");
        try {
            Order order = Order.newBuilder().addAllItemIds(items).build();
            OrderReceipt receipt = orderStub.makeOrder(order);

            String logString = String.format(
                "Order was made, %d items with total price %d $",
                receipt.getItemsCount(),
                receipt.getTotalPrice()
            );
            logger.info(logString);
        }
        catch (StatusRuntimeException ex) {
            logger.fatal("Cannot make an order, resource unavailable");
        }
    }

    public void shutdown() {
        try {
            managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException ex) {
            logger.error("Failed to stop gRPC channel", ex);
        }
    }
}

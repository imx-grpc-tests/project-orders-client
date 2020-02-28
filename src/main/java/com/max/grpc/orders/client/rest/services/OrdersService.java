
package com.max.grpc.orders.client.rest.services;

import com.max.grpc.orders.client.grpc.CafeClient;
import com.max.grpc.orders.client.grpc.OrdersAsyncClient;
import com.max.grpc.orders.client.grpc.ResponseObserver;
import com.max.grpc.orders.client.rest.mappers.OrderReceiptMapperImpl;
import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.proto.OrderReceipt;

import org.apache.log4j.Logger;

import java.util.List;

public class OrdersService {
    private CafeClient cafeClient;
    private OrdersAsyncClient ordersAsyncClient;
    private OrderReceiptMapperImpl orderReceiptMapper;
    private final Logger logger = Logger.getLogger(OrdersService.class);

    public OrdersService(CafeClient cafeClient, OrderReceiptMapperImpl orderReceiptMapper) {
        this.cafeClient = cafeClient;
        this.orderReceiptMapper = orderReceiptMapper;

        var managedChannel = cafeClient.getManagedChannel();
        this.ordersAsyncClient = new OrdersAsyncClient(managedChannel);
    }

    public ApiOrderReceipt makeOrder(List<String> itemsIds) {
        logger.info("Making order, " + itemsIds.size() + " items...");
        OrderReceipt receipt = cafeClient.makeOrder(itemsIds);
        logger.info("Order has been made successfully, " + receipt.getTotalPrice() + "$");

        logger.info("Forming response...");
        return orderReceiptMapper.protoToRestModel(receipt);
    }

    public void makeOrderAsync(List<String> itemsIds, ResponseObserver<ApiOrderReceipt> observer) {
        ordersAsyncClient.makeOrder(itemsIds, new ResponseObserver<>() {
            @Override
            public void onNext(OrderReceipt receipt) {
                logger.info("Order has been made successfully, " + receipt.getTotalPrice() + "$");
                observer.onNext(orderReceiptMapper.protoToRestModel(receipt));
            }

            @Override
            public void onError(Throwable throwable) {
                observer.onError(throwable);
            }

            @Override
            public void onCompleted() {
                observer.onCompleted();
            }
        });
    }
}


package com.max.grpc.orders.client.grpc;

import com.max.grpc.orders.proto.Order;
import com.max.grpc.orders.proto.OrderReceipt;
import com.max.grpc.orders.proto.OrderServiceGrpc;
import com.max.grpc.orders.proto.OrderServiceGrpc.OrderServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;

import java.util.List;

public class OrdersAsyncClient {
    private final Logger logger = Logger.getLogger(OrdersAsyncClient.class);
    private OrderServiceStub asyncStub;

    public OrdersAsyncClient(ManagedChannel channel) {
        this.asyncStub = OrderServiceGrpc.newStub(channel);
    }

    public void makeOrder(List<String> items, ResponseObserver<OrderReceipt> callback) {
        try {
            Order order = Order.newBuilder().addAllItemIds(items).build();
            asyncStub.makeOrder(order, new StreamObserver<>() {
                @Override
                public void onNext(OrderReceipt receipt) {
                    logger.info("make-order-async: receipt received");
                    callback.onNext(receipt);
                }

                @Override
                public void onError(Throwable throwable) {
                    String message = "make-order-async: Error occured during request";
                    logger.error(message, throwable);
                    callback.onError(throwable);
                }

                @Override
                public void onCompleted() {
                    logger.info("make-order-async: request completed");
                    callback.onCompleted();
                }
            });
        }
        catch (StatusRuntimeException ex) {
            logger.error("make-order-async: resource unavailable", ex);
            callback.onError(ex);
        }
    }
}

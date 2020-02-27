
package com.max.grpc.orders.client.grpc;

import io.grpc.stub.StreamObserver;

public interface ResponseObserver<T> extends StreamObserver<T> {
    @Override
    default void onNext(T t) {};

    @Override
    default void onError(Throwable throwable) {};

    @Override
    default void onCompleted() {};
}

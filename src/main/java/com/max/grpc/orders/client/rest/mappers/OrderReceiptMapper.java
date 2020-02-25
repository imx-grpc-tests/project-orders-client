
package com.max.grpc.orders.client.rest.mappers;

import com.max.grpc.orders.client.rest.models.ApiOrderReceipt;
import com.max.grpc.orders.proto.OrderReceipt;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderReceiptMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "itemsList", target = "items")
    @Mapping(source = "totalPrice", target = "totalPrice")
    ApiOrderReceipt protoToRestModel(OrderReceipt orderReceipt);
}

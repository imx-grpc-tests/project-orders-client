
package com.max.grpc.orders.client.rest.mappers;

import com.max.grpc.orders.client.rest.models.ApiCafeMenu;
import com.max.grpc.orders.proto.CafeMenu;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CafeMenuMapper {
    @Mapping(source = "itemsList", target = "items")
    ApiCafeMenu protoToRestModel(CafeMenu cafeMenu);
}

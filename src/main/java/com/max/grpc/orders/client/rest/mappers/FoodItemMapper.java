
package com.max.grpc.orders.client.rest.mappers;

import com.max.grpc.orders.client.rest.models.ApiFoodItem;
import com.max.grpc.orders.proto.FoodItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FoodItemMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "type", target = "foodType")
    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "price", target = "price")
    ApiFoodItem protoToRestModel(FoodItem foodItem);
}

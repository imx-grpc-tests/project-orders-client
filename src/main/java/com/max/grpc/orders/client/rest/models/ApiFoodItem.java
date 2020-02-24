
package com.max.grpc.orders.client.rest.models;

import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.FoodType;

public class ApiFoodItem {
    private String id;
    private String title;
    private FoodType foodType;
    private double weight;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static ApiFoodItem from(FoodItem protoItem) {
        var responseItem = new ApiFoodItem();
        responseItem.setId(protoItem.getId());
        responseItem.setTitle(protoItem.getTitle());
        responseItem.setFoodType(protoItem.getType());
        responseItem.setWeight(protoItem.getWeight());
        responseItem.setPrice(protoItem.getPrice());
        return responseItem;
    }
}

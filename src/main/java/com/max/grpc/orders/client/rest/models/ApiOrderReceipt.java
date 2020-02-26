
package com.max.grpc.orders.client.rest.models;

import java.util.List;

public class ApiOrderReceipt {
    private String id;
    private long date;
    private List<ApiFoodItem> items;
    private int totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<ApiFoodItem> getItems() {
        return items;
    }

    public void setItems(List<ApiFoodItem> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

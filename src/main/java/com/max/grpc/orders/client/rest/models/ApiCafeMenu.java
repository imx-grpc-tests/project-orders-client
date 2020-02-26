
package com.max.grpc.orders.client.rest.models;

import java.util.List;

public class ApiCafeMenu {
    private List<ApiFoodItem> items;

    public List<ApiFoodItem> getItems() {
        return items;
    }

    public void setItems(List<ApiFoodItem> items) {
        this.items = items;
    }
}

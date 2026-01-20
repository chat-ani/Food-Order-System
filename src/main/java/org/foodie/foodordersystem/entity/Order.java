package org.foodie.foodordersystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Order {
    private final String orderId;
    private final String user;
    private final Map<String, Integer> items;
    private final SelectionType selectionType;
    private OrderStatus status = OrderStatus.ACCEPTED;
    private Restaurant restaurant;

    public Order(String orderId,
                 String user,
                 Map<String, Integer> items,
                 SelectionType selectionType) {
        this.orderId = orderId;
        this.user = user;
        this.items = Map.copyOf(items);
        this.selectionType = selectionType;
    }

    public void assignRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void complete() {
        this.status = OrderStatus.COMPLETED;
    }
}

package org.anirban.foodordersystem.repository;

import org.anirban.foodordersystem.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order find(String orderId) {
        return orders.get(orderId);
    }
}

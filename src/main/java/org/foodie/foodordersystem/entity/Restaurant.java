package org.foodie.foodordersystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.foodie.foodordersystem.util.BillCalculator;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Restaurant {
    private final String name;
    private final double rating;
    private final int maxOrders;
    private final Map<String, Integer> menu = new HashMap<>();
    private int activeOrders = 0;

    public Restaurant(String name, double rating, int maxOrders) {
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
    }

    public synchronized boolean canAcceptOrder() {
        return activeOrders < maxOrders;
    }

    public synchronized void acceptOrder() {
        if (!canAcceptOrder()) {
            throw new IllegalStateException("Restaurant capacity full");
        }
        activeOrders++;
    }

    public synchronized void completeOrder() {
        activeOrders--;
    }

    public boolean canFulfill(Map<String, Integer> items) {
        return menu.keySet().containsAll(items.keySet());
    }

    public int calculateBill(Map<String, Integer> items) {
        return BillCalculator.calculateBill(menu, items);

    }

    public void addMenuItem(String item, int price) {
        menu.putIfAbsent(item, price);
    }

    public void updateMenuItem(String item, int price) {
        if (!menu.containsKey(item)) {
            throw new IllegalArgumentException("Menu item does not exist");
        }
        menu.put(item, price);
    }

}

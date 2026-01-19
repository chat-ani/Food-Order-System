package org.anirban.foodordersystem.dto;

import java.util.Map;

public class RestaurantRequest {
    public String name;
    public double rating;
    public int maxOrders;
    public Map<String, Integer> menu;
}

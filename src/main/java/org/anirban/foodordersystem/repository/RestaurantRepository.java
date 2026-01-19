package org.anirban.foodordersystem.repository;

import org.anirban.foodordersystem.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepository {
    private final Map<String, Restaurant> data = new HashMap<>();

    public void save(Restaurant restaurant) {
        data.put(restaurant.getName(), restaurant);
    }

    public Restaurant findByName(String name) {
        return data.get(name);
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(data.values());
    }
}

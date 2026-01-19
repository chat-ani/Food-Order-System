package org.anirban.foodordersystem.service;

import lombok.Setter;
import org.anirban.foodordersystem.entity.Restaurant;
import org.anirban.foodordersystem.exception.RestaurantException;
import org.anirban.foodordersystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Setter
public class RestaurantService {
    @Autowired
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public void onboard(Restaurant restaurant) {
        repository.save(restaurant);
    }

    public void addMenuItems(String name, Map<String, Integer> menu) {
        Restaurant restaurant = repository.findByName(name);
        if (restaurant == null) {
            throw new RestaurantException("Restaurant not found");
        }
        menu.forEach(restaurant::addMenuItem);
    }

    public void updateMenuItems(String name, Map<String, Integer> menu) {
        Restaurant restaurant = repository.findByName(name);
        if (restaurant == null) {
            throw new RestaurantException("Restaurant not found");
        }
        menu.forEach(restaurant::updateMenuItem);
    }

    public Restaurant getRestaurant(String name) {
        Restaurant restaurant = repository.findByName(name);
        if (restaurant == null) {
            throw new RestaurantException("Restaurant not found");
        }
        return restaurant;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}

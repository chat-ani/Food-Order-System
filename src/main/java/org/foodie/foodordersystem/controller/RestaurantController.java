package org.foodie.foodordersystem.controller;

import org.foodie.foodordersystem.dto.MenuUpdateRequest;
import org.foodie.foodordersystem.dto.RestaurantRequest;
import org.foodie.foodordersystem.entity.Restaurant;
import org.foodie.foodordersystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> onboardRestaurant(
            @RequestBody RestaurantRequest request) {

        Restaurant restaurant =
                new Restaurant(request.name, request.rating, request.maxOrders);

        request.menu.forEach(restaurant::addMenuItem);
        service.onboard(restaurant);

        return ResponseEntity.ok("Restaurant onboarded successfully");
    }

    @PutMapping("/{name}/menu/add")
    public ResponseEntity<String> addMenuItems(
            @PathVariable String name,
            @RequestBody MenuUpdateRequest request) {

        service.addMenuItems(name, request.menu);
        return ResponseEntity.ok("Menu items added successfully");
    }

    @PutMapping("/{name}/menu/update")
    public ResponseEntity<String> updateMenuItems(
            @PathVariable String name,
            @RequestBody MenuUpdateRequest request) {

        service.updateMenuItems(name, request.menu);
        return ResponseEntity.ok("Menu items updated successfully");
    }

    @GetMapping("/{name}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("name") String name){
        Restaurant restaurant = service.getRestaurant(name);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public List<String> listRestaurants() {
        return service.getAll()
                .stream()
                .map(Restaurant::getName)
                .toList();
    }
}

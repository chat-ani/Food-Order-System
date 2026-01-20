package org.foodie.foodordersystem.service;

import lombok.Setter;
import org.foodie.foodordersystem.entity.Order;
import org.foodie.foodordersystem.entity.Restaurant;
import org.foodie.foodordersystem.exception.OrderAssignmentException;
import org.foodie.foodordersystem.repository.OrderRepository;
import org.foodie.foodordersystem.repository.RestaurantRepository;
import org.foodie.foodordersystem.strategy.SelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Setter
public class OrderService {
    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final OrderRepository orderRepository;
    private final Map<String, SelectionStrategy> strategies;

    public OrderService(RestaurantRepository restaurantRepository,
                        OrderRepository orderRepository,
                        Map<String, SelectionStrategy> strategies) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.strategies = strategies;
    }

    public Order placeOrder(Order order) {

        List<Restaurant> eligible = restaurantRepository.findAll().stream()
                .filter(Restaurant::canAcceptOrder)
                .filter(r -> r.canFulfill(order.getItems()))
                .toList();

        if (eligible.isEmpty()) {
            throw new OrderAssignmentException("Cannot assign the order");
        }

        SelectionStrategy strategy =
                strategies.get(order.getSelectionType().name());

        Restaurant selected = strategy.select(eligible, order.getItems());
        selected.acceptOrder();

        order.assignRestaurant(selected);
        orderRepository.save(order);

        return order;
    }

    public void completeOrder(String orderId) {
        Order order = orderRepository.find(orderId);
        order.getRestaurant().completeOrder();
        order.complete();
    }
}

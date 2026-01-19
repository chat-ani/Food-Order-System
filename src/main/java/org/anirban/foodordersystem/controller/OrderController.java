package org.anirban.foodordersystem.controller;

import org.anirban.foodordersystem.dto.OrderRequest;
import org.anirban.foodordersystem.dto.OrderResponse;
import org.anirban.foodordersystem.entity.Order;
import org.anirban.foodordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {

        Order order = new Order(
                request.orderId,
                request.user,
                request.items,
                request.selectionType
        );

        Order placedOrder = service.placeOrder(order);

        OrderResponse response = new OrderResponse();
        response.orderId = placedOrder.getOrderId();
        response.restaurantName = placedOrder.getRestaurant().getName();
        response.status = placedOrder.getStatus().name();

        return response;
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<String> completeOrder(
            @PathVariable String orderId) {

        service.completeOrder(orderId);
        return ResponseEntity.ok("Order marked as COMPLETED");
    }
}
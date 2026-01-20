package org.foodie.foodordersystem.controller;

import org.foodie.foodordersystem.dto.OrderRequest;
import org.foodie.foodordersystem.dto.OrderResponse;
import org.foodie.foodordersystem.entity.Order;
import org.foodie.foodordersystem.exception.OrderAssignmentException;
import org.foodie.foodordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {

        try{
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

            return ResponseEntity.ok(response);
        } catch (OrderAssignmentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot assign the order");
        }
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<String> completeOrder(
            @PathVariable String orderId) {

        service.completeOrder(orderId);
        return ResponseEntity.ok("Order marked as COMPLETED");
    }
}
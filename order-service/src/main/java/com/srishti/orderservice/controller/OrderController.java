package com.srishti.orderservice.controller;

import com.srishti.orderservice.dto.OrderResponse;
import com.srishti.orderservice.model.Order;
import com.srishti.orderservice.model.OrderStatus;
import com.srishti.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@RequestParam String orderId,  @RequestBody OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
    }
}

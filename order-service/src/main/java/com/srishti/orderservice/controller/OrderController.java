package com.srishti.orderservice.controller;

import com.srishti.orderservice.dto.OrderResponse;
import com.srishti.orderservice.model.Order;
import com.srishti.orderservice.model.OrderStatusUpdateMsg;
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

    @PutMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderAfterPayment(@RequestParam String orderId,
                                        @RequestBody OrderStatusUpdateMsg orderStatusUpdateMsg) {
        orderService.updateOrderAfterPayment(orderId, orderStatusUpdateMsg);
    }
}

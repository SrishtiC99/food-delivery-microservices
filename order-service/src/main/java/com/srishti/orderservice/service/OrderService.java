package com.srishti.orderservice.service;

import com.srishti.orderservice.dto.OrderItemDto;
import com.srishti.orderservice.dto.OrderResponse;
import com.srishti.orderservice.model.Order;
import com.srishti.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public OrderResponse createOrder(Order order) {
        order.setOrderTime(System.currentTimeMillis());
        order.setTotalAmount(BigDecimal.valueOf(order.getOrderItems().stream()
                .mapToInt(e -> e.getPrice() * e.getQuantity()).sum()));
        orderRepository.save(order);

        return OrderResponse.builder()
                .orderItems(order.getOrderItems().stream().map(orderItem -> OrderItemDto.builder()
                        .name(orderItem.getName())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .build()).toList())
                .totalAmount(order.getTotalAmount())
                .orderTime(order.getOrderTime())
                .address(order.getAddress())
                .paymentId(order.getPaymentId())
                .build();
    }
}

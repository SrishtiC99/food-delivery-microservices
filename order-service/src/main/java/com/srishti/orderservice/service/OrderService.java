package com.srishti.orderservice.service;

import com.srishti.orderservice.dto.OrderItemDto;
import com.srishti.orderservice.dto.OrderResponse;
import com.srishti.orderservice.event.OrderPlacedNotification;
import com.srishti.orderservice.model.Order;
import com.srishti.orderservice.model.OrderItem;
import com.srishti.orderservice.model.OrderStatus;
import com.srishti.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedNotification> kafkaTemplate;
    public OrderResponse createOrder(Order order) {
        order.setOrderTime(System.currentTimeMillis());
        order.setTotalAmount(BigDecimal.valueOf(order.getOrderItems().stream()
                .mapToInt(e -> e.getPrice() * e.getQuantity()).sum()));
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        // call FoodItemService to update quantity of items after order is placed
        List<String> foodItemIds = order.getOrderItems().stream().map(OrderItem::getFoodItemId).toList();
        List<Integer> orderQuantities = order.getOrderItems().stream().map(OrderItem::getQuantity).toList();

        webClientBuilder.build().put()
                .uri("http://restaurant-service/api/v1/fooditem/quantity",
                        uriBuilder -> uriBuilder
                                .queryParam("foodItemIds", foodItemIds)
                                .queryParam("orderQuantities", orderQuantities)
                                .build())
                .exchange()
                .block();

        return OrderResponse.builder()
                .orderItems(order.getOrderItems().stream().map(orderItem -> OrderItemDto.builder()
                        .name(orderItem.getName())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .build()).toList())
                .totalAmount(order.getTotalAmount())
                .orderTime(order.getOrderTime())
                .orderStatus(order.getOrderStatus())
                .address(order.getAddress())
                .build();
    }

    public void updateOrderAfterPayment(List<String> paymentInfo) {
        Optional<Order> optionalOrder = orderRepository.findById(paymentInfo.get(1));
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if(paymentInfo.get(2).equals("SUCCESS")) {
                order.setPaymentId(paymentInfo.get(0));
                order.setOrderStatus(OrderStatus.COMPLETED);
                order.setDeliveryTime(order.getOrderTime() + 30*60*1000);
                orderRepository.save(order);

                // send notification to restaurant
                sendNotificationToRestaurant(order);

                // send this order to delivery service
            }
            else {
                order.setOrderStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
            }
        }
    }

    private void sendNotificationToRestaurant(Order order) {
        // Send a notification to Restaurant Service to prepare this order
        OrderPlacedNotification notification = OrderPlacedNotification.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .orderAddress(order.getAddress())
                .foodItemIds(order.getOrderItems().stream()
                        .map(OrderItem::getFoodItemId).toList())
                .foodItemQuantities(order.getOrderItems().stream()
                        .map(OrderItem::getQuantity).toList())
                .build();
        kafkaTemplate.send("order-rest-notification-topic", notification);
    }

    public void updateOrderStatus(String orderId, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(orderStatus);
        }
    }
}

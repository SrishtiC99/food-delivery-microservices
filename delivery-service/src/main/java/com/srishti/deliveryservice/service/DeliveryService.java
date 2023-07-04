package com.srishti.deliveryservice.service;

import com.srishti.deliveryservice.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public String deliverOrder(String orderId) {
        // update order status to delivered
        webClientBuilder.build().put()
                .uri("http://order-service/api/v1/order/status",
                        uriBuilder -> uriBuilder
                                .queryParam("orderId", orderId)
                                .build())
                .bodyValue(OrderStatus.DELIVERED)
                .exchange()
                .block();
        return "Order with id: " + orderId + " delivered";
    }
}

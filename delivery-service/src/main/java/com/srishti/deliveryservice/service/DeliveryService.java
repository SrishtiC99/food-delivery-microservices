package com.srishti.deliveryservice.service;

import com.srishti.deliveryservice.model.DeliveryAgent;
import com.srishti.deliveryservice.model.DeliveryAssignment;
import com.srishti.deliveryservice.model.OrderDetails;
import com.srishti.deliveryservice.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

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

    // This method creates a new delivery assignment after receiving a delivery request from
    // order-service
    
     public void receiveOrderAssignment() {
        createNewAssignment(new OrderDetails());
     }
     private void createNewAssignment(OrderDetails orderDetails) {
        // search for a delivery Agent - talk to auth service for that
        DeliveryAgent[] deliveryAgents = webClientBuilder.build().get()
                .uri("http://auth-service/api/v1/user/delivery",
                        UriBuilder::build)
                .retrieve()
                .bodyToMono(DeliveryAgent[].class)
                .block();
        
        // TODO: sort these agents based on the distance between there current location and restaurant location
         DeliveryAssignment deliveryAssignment = DeliveryAssignment.builder()
                 .deliveryAgentName(deliveryAgents[0].getName())
                 .deliveryAgentCurrentAddress(deliveryAgents[0].getAddress())
                 .deliveryAgentPhoneNumber(deliveryAgents[0].getPhoneNumber())
                 .orderDetails(orderDetails)
                 .build();
         
         // TODO: send a notification to deliveryAgent that an order is assigned
         // TODO: if agent accepts the assignment track his location
    }

    public void callCustomer() {
    }
}

package com.srishti.restaurantservice;

import com.srishti.restaurantservice.utils.OrderPlacedNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class RestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

    @KafkaListener(topics = "order-rest-notification-topic")
    public void receiveNotification(OrderPlacedNotification notification) {
        log.info("Received notification for order id: {}.", notification.getOrderId());
        // prepare food
        // search for delivery man

    }
}

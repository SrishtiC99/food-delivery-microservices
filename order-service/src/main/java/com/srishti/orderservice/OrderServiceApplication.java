package com.srishti.orderservice;

import com.srishti.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@SpringBootApplication
@Slf4j
public class OrderServiceApplication {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @KafkaListener(topics = "payment-notification-topic")
    public void receivePaymentNotification(List<String> paymentInfo) {
        log.info("Received payment notification for order id: {}.", paymentInfo.get(1));
        orderService.updateOrderAfterPayment(paymentInfo);
    }
}

package com.srishti.paymentservice.service;

import com.srishti.paymentservice.model.Payment;
import com.srishti.paymentservice.model.PaymentStatus;
import com.srishti.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private final KafkaTemplate<String, List<String>> kafkaTemplate;
    public String processPayment(Payment payment) {
        //TODO: Check for credit card validity
        //TODO: call some 3rd party to handle payment request
        payment.setTimestamp(System.currentTimeMillis());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment = paymentRepository.save(payment);

        // send payment info to order-service
        //TODO: getting error for custom data object
        List<String> paymentInfo = new ArrayList<>();
        paymentInfo.add(payment.getId());
        paymentInfo.add(payment.getOrderId());
        paymentInfo.add("SUCCESS");
        kafkaTemplate.send("payment-notification-topic", paymentInfo);

        return "payment was " + payment.getPaymentStatus();
    }
}

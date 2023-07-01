package com.srishti.paymentservice.service;

import com.netflix.discovery.converters.Auto;
import com.srishti.paymentservice.model.OrderStatusUpdateMsg;
import com.srishti.paymentservice.model.Payment;
import com.srishti.paymentservice.model.PaymentStatus;
import com.srishti.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    public String processPayment(Payment payment) {
        //TODO: Check for credit card validity
        //TODO: call some 3rd party to handle payment request
        payment.setTimestamp(System.currentTimeMillis());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment = paymentRepository.save(payment);

        // send payment info to order-service
        Payment finalPayment = payment;
        webClientBuilder.build().put()
                .uri("http://order-service/api/v1/order/payment",
                        uriBuilder -> uriBuilder
                                .queryParam("orderId", finalPayment.getOrderId())
                                .build())
                .bodyValue(new OrderStatusUpdateMsg(finalPayment.getId(), finalPayment.getPaymentStatus()))
                .exchange()
                .block();

        return "payment was " + payment.getPaymentStatus();
    }
}

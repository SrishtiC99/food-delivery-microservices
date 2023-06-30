package com.srishti.paymentservice.service;

import com.srishti.paymentservice.model.Payment;
import com.srishti.paymentservice.model.PaymentStatus;
import com.srishti.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public String processPayment(Payment payment) {
        payment.setTimestamp(System.currentTimeMillis());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment = paymentRepository.save(payment);
        return "payment was " + payment.getPaymentStatus();
    }
}

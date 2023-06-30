package com.srishti.paymentservice.service;

import com.srishti.paymentservice.dto.PaymentResponse;
import com.srishti.paymentservice.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentResponse createPayment(Payment payment) {
        // Logic for payment
        // if payment was done successfully then insert payment request into the database
        return new PaymentResponse();
    }
}

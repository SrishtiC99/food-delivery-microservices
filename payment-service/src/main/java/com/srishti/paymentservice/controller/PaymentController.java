package com.srishti.paymentservice.controller;

import com.srishti.paymentservice.dto.PaymentResponse;
import com.srishti.paymentservice.model.Payment;
import com.srishti.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse makePayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }
}

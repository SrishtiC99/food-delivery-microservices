package com.srishti.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusUpdateMsg {
    private String paymentId;
    private PaymentStatus paymentStatus;
}

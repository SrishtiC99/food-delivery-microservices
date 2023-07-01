package com.srishti.orderservice.model;

public enum OrderStatus {
    PENDING,              // has been created and is waiting for payment
    COMPLETED,            // paid successfully
    CANCELLED,            // order has been cancelled
    DELIVERING,         // is on the way for delivery
    DELIVERED           // items have been delivered.
}

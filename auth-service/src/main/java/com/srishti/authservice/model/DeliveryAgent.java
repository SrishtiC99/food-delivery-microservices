package com.srishti.authservice.model;

import lombok.Builder;

@Builder
public class DeliveryAgent {
    private String name;
    private Address address;
    private Long phoneNumber;
}

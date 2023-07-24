package com.srishti.deliveryservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeliveryAgent {
    private String name;
    private Address address;
    private Long phoneNumber;
}

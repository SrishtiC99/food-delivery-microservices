package com.srishti.restaurantservice.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderPlacedNotification {
    private String orderId;
    private List<String> foodItemIds;
    private List<Integer> foodItemQuantities;
    private Address orderAddress;
    private String userId;
}

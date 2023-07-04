package com.srishti.orderservice.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.srishti.orderservice.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class OrderPlacedNotification {
    private String orderId;
    private List<String> foodItemIds;
    private List<Integer> foodItemQuantities;
    private Address orderAddress;
    private String userId;

    @JsonCreator
    public OrderPlacedNotification(@JsonProperty("orderId") String orderId,
                                   @JsonProperty("foodItemIds") List<String> foodItemIds,
                                   @JsonProperty("foodItemQuantities") List<Integer> foodItemQuantities,
                                   @JsonProperty("orderAddress") Address orderAddress,
                                   @JsonProperty("userId") String userId) {
        this.orderId = orderId;
        this.foodItemIds = foodItemIds;
        this.foodItemQuantities = foodItemQuantities;
        this.orderAddress = orderAddress;
        this.userId = userId;
    }
}

package com.srishti.orderservice.event;

import com.srishti.orderservice.model.Address;
import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.util.Pair;

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

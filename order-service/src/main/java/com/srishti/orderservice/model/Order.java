package com.srishti.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private Long orderNumber;
    private String restaurantId;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private Long orderTime;
    private Address address;
    private String paymentId;
    private String userId;
}

package com.srishti.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderItemDto {
    private String name;
    private Integer price;
    private Integer quantity;
}

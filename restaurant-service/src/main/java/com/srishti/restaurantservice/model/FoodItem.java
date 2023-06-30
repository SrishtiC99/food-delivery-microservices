package com.srishti.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "foodItems")
public class FoodItem {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Binary icon;
    private Integer quantity;
    private String restaurantId;
}

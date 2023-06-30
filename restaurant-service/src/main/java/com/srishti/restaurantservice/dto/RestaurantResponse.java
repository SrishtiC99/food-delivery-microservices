package com.srishti.restaurantservice.dto;

import com.srishti.restaurantservice.model.Address;
import com.srishti.restaurantservice.model.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {
    private String id;
    private String name;
    private String description;
    private Address address;
    private List<Long> contactInfo;
    private Double rating;
    private List<FoodItem> foodItems;
}

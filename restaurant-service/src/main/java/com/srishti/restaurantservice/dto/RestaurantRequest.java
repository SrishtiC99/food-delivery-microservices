package com.srishti.restaurantservice.dto;

import com.srishti.restaurantservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String name;
    private String description;
    private Address address;
    private List<Long> contactInfo;
    private Double rating;
}

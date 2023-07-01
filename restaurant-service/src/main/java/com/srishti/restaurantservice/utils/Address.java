package com.srishti.restaurantservice.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String address;
    private String city;
    private String state;
    private Integer zipcode;
}

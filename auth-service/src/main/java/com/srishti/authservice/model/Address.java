package com.srishti.authservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Address {
    private String address;
    private String city;
    private String state;
    private Integer zipcode;
}

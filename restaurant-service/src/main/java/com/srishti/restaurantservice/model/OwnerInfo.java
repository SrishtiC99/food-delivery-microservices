package com.srishti.restaurantservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerInfo {
    private String username;
    private String fullName;
    private String phoneNumber;
}

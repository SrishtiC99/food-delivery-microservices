package com.srishti.userservice.dto;

import com.srishti.userservice.model.Address;
import com.srishti.userservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UserDto user;
    private Integer responseCode;
    private String msg;
}

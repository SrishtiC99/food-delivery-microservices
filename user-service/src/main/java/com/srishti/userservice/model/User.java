package com.srishti.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "user")
public class User {
    @Id
    private Long id;
    private String email;
    private String password;
    private Long phoneNumber;
    private Address address;
    private UserRole userRole;
}

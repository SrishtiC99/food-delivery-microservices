package com.srishti.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(collection = "users")
public class UserCredential {
    @Id
    private String id;
    private String fullName;
    private String username; //email
    private String password;
    private Long phoneNumber;
    private Address address;
    private UserRole userRole;
}

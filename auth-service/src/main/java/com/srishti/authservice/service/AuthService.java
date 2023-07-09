package com.srishti.authservice.service;

import com.srishti.authservice.dto.UserDto;
import com.srishti.authservice.dto.UserResponse;
import com.srishti.authservice.model.UserCredential;
import com.srishti.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userRepository.save(credential);
        return "User added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public UserResponse getUser(String id) {
        Optional<UserCredential> user = userRepository.findById(id);
        if(user.isPresent()) {
            UserCredential user1 = user.get();
            return mapUserToUserResponse(user1);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    public UserResponse updateUser(UserCredential user) {
        if(user.getId() == null) {
            return UserResponse.builder()
                    .responseCode(400)
                    .msg("Please provide user id")
                    .build();
        }
        Optional<UserCredential> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            return updateUser(userOptional.get(), user);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    private UserResponse updateUser(UserCredential user, UserCredential userNew) {
        user.setFullName(userNew.getFullName());
        user.setUsername(userNew.getUsername());
        user.setPhoneNumber(userNew.getPhoneNumber());
        user.setAddress(userNew.getAddress());
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    private UserResponse mapUserToUserResponse(UserCredential user1) {
        return UserResponse.builder()
                .user(UserDto.builder()
                        .id(user1.getId())
                        .fullName(user1.getFullName())
                        .email(user1.getUsername())
                        .phoneNumber(user1.getPhoneNumber())
                        .address(user1.getAddress())
                        .userRole(user1.getUserRole())
                        .build())
                .responseCode(200)
                .msg("Success")
                .build();
    }
}

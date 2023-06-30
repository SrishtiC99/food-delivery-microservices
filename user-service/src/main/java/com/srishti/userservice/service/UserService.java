package com.srishti.userservice.service;

import com.srishti.userservice.dto.UserDto;
import com.srishti.userservice.dto.UserResponse;
import com.srishti.userservice.model.User;
import com.srishti.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String createUser(User user) {
        userRepository.save(user);
        return "User with id: " + user.getId() + " created";
    }

    public UserResponse getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User user1 = user.get();
            return mapUserToUserResponse(user1);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    public UserResponse updateUser(User user) {
        if(user.getId() == null) {
            return UserResponse.builder()
                    .responseCode(400)
                    .msg("Please provide user id")
                    .build();
        }
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            return updateUser(userOptional.get(), user);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    private UserResponse updateUser(User user, User userNew) {
        user.setFullName(userNew.getFullName());
        user.setEmail(userNew.getEmail());
        user.setPhoneNumber(userNew.getPhoneNumber());
        user.setAddress(userNew.getAddress());
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    private UserResponse mapUserToUserResponse(User user1) {
        return UserResponse.builder()
                .user(UserDto.builder()
                        .id(user1.getId())
                        .fullName(user1.getFullName())
                        .email(user1.getEmail())
                        .phoneNumber(user1.getPhoneNumber())
                        .address(user1.getAddress())
                        .userRole(user1.getUserRole())
                        .build())
                .responseCode(200)
                .msg("Success")
                .build();
    }
}

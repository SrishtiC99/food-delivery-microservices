package com.srishti.authservice.controller;

import com.srishti.authservice.dto.UserResponse;
import com.srishti.authservice.model.DeliveryAgent;
import com.srishti.authservice.model.UserCredential;
import com.srishti.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("id") String id, @RequestHeader("loggedInUser") String username) {
        // No authorization needed for Get User
        System.out.println("Get user with username: "+ username);
        return authService.getUser(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@RequestBody UserCredential user, @RequestHeader("loggedInUser") String username) {
        // Only user can update his/her details check if the User in request is same as the loggedInUser or not
        if(!user.getUsername().equals(username)) {
            return UserResponse.builder()
                    .responseCode(403)
                    .msg("Access Denied")
                    .build();
        }
        return authService.updateUser(user);
    }

    @GetMapping("/delivery")
    public List<DeliveryAgent> getDeliveryAgents() {
        return authService.getDeliveryAgents();
    }

    @GetMapping("/role")
    public String getUserRole(String username) {
        return authService.getUserRole(username);
    }
}

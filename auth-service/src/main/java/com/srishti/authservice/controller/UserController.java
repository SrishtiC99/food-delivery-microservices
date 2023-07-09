package com.srishti.authservice.controller;

import com.srishti.authservice.dto.UserResponse;
import com.srishti.authservice.model.UserCredential;
import com.srishti.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("id") String id, @RequestHeader("loggedInUser") String username) {
        System.out.println("Get user with username: "+ username);
        return authService.getUser(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@RequestBody UserCredential user, @RequestHeader("loggedInUser") String username) {
        System.out.println("Update user with username: "+ username);
        return authService.updateUser(user);
    }
}

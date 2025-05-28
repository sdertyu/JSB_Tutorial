package com.pngo.crudProj.controller;

import com.pngo.crudProj.dto.request.ApiResponse;
import com.pngo.crudProj.dto.request.UserCreate;
import com.pngo.crudProj.dto.request.UserUpdate;
import com.pngo.crudProj.dto.response.UserResponse;
import com.pngo.crudProj.entities.User;
import com.pngo.crudProj.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreate request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setData(userService.createUser(request));
        return response;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        var authenication = SecurityContextHolder.getContext().getAuthentication();
        log.warn(authenication.getName());
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@RequestBody UserUpdate user, @PathVariable String id) {
        return userService.updateUser(id,user);

    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}

package com.miniproject.wtlmini.controllers;

import com.miniproject.wtlmini.dto.user.CreateUserDto;
import com.miniproject.wtlmini.dto.user.LoginDto;
import com.miniproject.wtlmini.dto.user.UpdatePassDto;
import com.miniproject.wtlmini.entity.User;
import com.miniproject.wtlmini.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @Operation(summary = "Get all users")
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Create new user")
    @PostMapping("/create")
    public User saveUser(@RequestBody CreateUserDto user) {
        return userService.saveUser(user);
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @Operation(summary = "Update password")
    @PostMapping("/update-password")
    public String updatePassword(@RequestBody UpdatePassDto updatePassDto) {
        return userService.updatePassword(updatePassDto);
    }

}

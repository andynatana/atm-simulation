package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.pojo.WSResponse;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public WSResponse create(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new WSResponse(HttpStatus.CREATED.value(), "User created successfully !", savedUser);
    }
}

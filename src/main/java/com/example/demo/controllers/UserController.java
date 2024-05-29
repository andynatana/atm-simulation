package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User connectedUser = userService.login(user.getMail(), user.getPassword());
        return ResponseEntity.ok(connectedUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }
}

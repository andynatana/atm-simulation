package com.example.demo.controllers;

import com.example.demo.entity.AccountCategory;
import com.example.demo.service.impl.AccountCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-category")
@AllArgsConstructor
public class AccountCategoryController {

    private final AccountCategoryService accountCategoryService;

    @PostMapping("/create")
    public ResponseEntity<AccountCategory> create(@RequestBody AccountCategory accountCategory) {
        AccountCategory category = accountCategoryService.save(accountCategory);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(category);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountCategory>> findAll() {
        List<AccountCategory> categoryList = accountCategoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }
}

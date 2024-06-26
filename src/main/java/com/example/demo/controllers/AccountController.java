package com.example.demo.controllers;


import com.example.demo.entity.Account;
import com.example.demo.pojo.ws.params.AccountCreationParam;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/check-balance")
    public ResponseEntity<AccountDTO> checkBalance(@RequestParam("userId") Long userId, @RequestParam("accountCategoryId") Long accountCategoryId) {
        AccountDTO account = accountService.getBalance(userId, accountCategoryId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestBody AccountCreationParam accountCreationParam) {
        Account account = accountService.create(accountCreationParam);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(account);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accountList = accountService.findAll();
        return ResponseEntity.ok(accountList);
    }
}

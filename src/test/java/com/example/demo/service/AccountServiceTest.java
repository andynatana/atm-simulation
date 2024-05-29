package com.example.demo.service;

import com.example.demo.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void findByAccountNumber() {
        final String accountNumber = "05001330688";
        Account account = accountService.findByAccountNumber(accountNumber);
        assertNotNull(account);
        assertEquals(accountNumber, account.getAccountNumber());
    }
}
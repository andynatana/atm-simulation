package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.service.impl.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceTest {

    private static final String ACCOUNT_NUMBER = "05001330688";

    @Autowired
    private AccountService accountService;

    @Test
    void findByAccountNumber() {
        Account account = accountService.findByAccountNumber(ACCOUNT_NUMBER);
        assertNotNull(account);
        assertEquals(ACCOUNT_NUMBER, account.getAccountNumber());
    }

    @Test
    void getBalance() {
        AccountDTO accountDTO = accountService.getBalance(ACCOUNT_NUMBER);
        assertNotNull(accountDTO);
        assertTrue(accountDTO.balance().compareTo(BigDecimal.ZERO) >= 0);
    }
}
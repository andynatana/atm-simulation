package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.pojo.ws.response.AccountDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

     private final static String ACCOUNT_NUMBER = "05001330688";

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @Rollback(value = false)
    void testProcess() throws BalanceInsufficientException {
        final BigDecimal previousBalance = accountService.findByAccountNumber(ACCOUNT_NUMBER).getBalance();
        final BigDecimal withdrawAmount = BigDecimal.valueOf(60);

        String transactionReference = transactionService.process(withdrawAmount, ACCOUNT_NUMBER);
        AccountDTO accountDTO = accountService.getBalance(ACCOUNT_NUMBER);
        assertNotNull(transactionReference);
        assertEquals(previousBalance.subtract(withdrawAmount), accountDTO.balance());
    }
}
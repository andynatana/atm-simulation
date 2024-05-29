package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.service.impl.AccountService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WithdrawalServiceTest {

     private final static String ACCOUNT_NUMBER = "05001330688";

    @Autowired
    private OperationService operationService;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @Rollback(value = false)
    void testProcess() throws BalanceInsufficientException {
        Account account = accountService.findByAccountNumber(ACCOUNT_NUMBER);
        final BigDecimal previousBalance = account.getBalance();
        final BigDecimal withdrawAmount = BigDecimal.valueOf(740);

        String transactionReference = operationService.process(withdrawAmount, account);
        AccountDTO accountDTO = accountService.getBalance(ACCOUNT_NUMBER);
        assertNotNull(transactionReference);
        assertEquals(previousBalance.subtract(withdrawAmount), accountDTO.balance());
    }
}
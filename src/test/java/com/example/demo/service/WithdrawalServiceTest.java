package com.example.demo.service;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.factory.OperationBuilderFactory;
import com.example.demo.factory.OperationServiceFactory;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.service.impl.AccountService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.example.demo.entity.enums.OperationType.WITHDRAW;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WithdrawalServiceTest {

    private final static String ACCOUNT_NUMBER = "05001330688";

    @Autowired
    private OperationServiceFactory operationServiceFactory;

    @Autowired
    private OperationBuilderFactory operationBuilderFactory;

    @Autowired
    private AccountService accountService;


    @Test
    @Transactional
    void testProcess() {
        //initializing account
        Account account = accountService.findByAccountNumber(ACCOUNT_NUMBER);
        final BigDecimal previousBalance = account.getBalance();
        final BigDecimal withdrawAmount = BigDecimal.valueOf(740);

        //building operation
        OperationBuilder operationBuilder = operationBuilderFactory.getInstance(WITHDRAW);
        Operation operation = operationBuilder.build(withdrawAmount, account);

        //initializing withdrawalService
        OperationService withdrawalService = operationServiceFactory.getInstance(WITHDRAW);
        String transactionReference = withdrawalService.process(operation);

        //test get balance
        AccountDTO accountDTO = accountService.getBalance(ACCOUNT_NUMBER);
        assertNotNull(transactionReference);
        assertTrue(previousBalance.compareTo(accountDTO.balance()) > 0);
    }
}
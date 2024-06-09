package com.example.demo.service;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.factory.OperationBuilderFactory;
import com.example.demo.factory.OperationServiceFactory;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.service.impl.AccountService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.example.demo.entity.enums.OperationType.DEPOSIT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DepositServiceTest {

    private final static String ACCOUNT_NUMBER = "05001330688";
    private final static OperationType OPERATION_TYPE = DEPOSIT;

    @Autowired
    private OperationServiceFactory operationServiceFactory;

    @Autowired
    private OperationBuilderFactory operationBuilderFactory;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    void testDeposit() {
        //initializing account
        Account account = accountService.findByAccountNumber(ACCOUNT_NUMBER);
        final BigDecimal previousBalance = account.getBalance();
        final BigDecimal depositAmount = BigDecimal.valueOf(740);

        //building operation
        OperationBuilder operationBuilder = operationBuilderFactory.getInstance(OPERATION_TYPE);
        Operation operation = operationBuilder.build(depositAmount, account);

        //initializing depositService
        OperationService depositService = operationServiceFactory.getInstance(OPERATION_TYPE);
        String transactionReference = depositService.process(operation);

        //test get balance
        AccountDTO accountDTO = accountService.getBalance(ACCOUNT_NUMBER);
        assertNotNull(transactionReference);

        System.out.println(previousBalance);
        System.out.println(accountDTO.balance());
        assertTrue(previousBalance.compareTo(accountDTO.balance()) < 0);
    }

}

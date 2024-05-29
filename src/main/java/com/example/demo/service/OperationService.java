package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.exception.BalanceInsufficientException;

import java.math.BigDecimal;

public interface OperationService {
    
    default String process(BigDecimal amount, Account account) throws BalanceInsufficientException {

        //create transaction reference and the object transaction
        Operation operation = createOperationInstance(amount, account);

        //create a transaction of type withdraw
        persistData(amount,account,operation);

        //Return reference
        return operation.getReference();
    }

    void persistData(BigDecimal amount,Account account, Operation operation);

    Operation createOperationInstance(BigDecimal amount, Account account);
}

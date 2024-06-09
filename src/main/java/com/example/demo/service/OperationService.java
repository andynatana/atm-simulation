package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;

import java.math.BigDecimal;

public interface OperationService {
    
    default String process(Operation operation) {

        //processing the operation
        updateAccount(operation.getAccount(), operation.getAmount());

        //create a transaction of type withdraw
        persistData(operation);

        //Return reference
        return operation.getReference();
    }

    void updateAccount(Account account, BigDecimal amount);

    void persistData(Operation operation);

}

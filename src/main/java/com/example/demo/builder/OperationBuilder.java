package com.example.demo.builder;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.utils.OperationReferenceGenerator;

import java.math.BigDecimal;

public interface OperationBuilder {

    default Operation build(BigDecimal amount, Account account) throws OperationException {
        validateData(amount, account);
        String transactionReference = generateReference();
        Operation operation = new Operation();
        operation.setAmount(amount);
        operation.setReference(transactionReference);
        operation.setAccount(account);
        operation.setType(getType());
        return operation;
    }

    String generateReference();

    void validateData(BigDecimal amount, Account account) throws OperationException;

    OperationType getType();
}

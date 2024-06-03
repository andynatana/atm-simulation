package com.example.demo.validators;

import com.example.demo.entity.Account;
import com.example.demo.exception.operation.BalanceInsufficientException;

import java.math.BigDecimal;

public interface OperationDataValidator {

    void validate(BigDecimal amount, Account account) throws BalanceInsufficientException;
}

package com.example.demo.validators;

import com.example.demo.entity.Account;
import com.example.demo.exception.BalanceInsufficientException;

import java.math.BigDecimal;

public interface OperationDataValidator {

    boolean validate(BigDecimal amount, Account account) throws BalanceInsufficientException;
}
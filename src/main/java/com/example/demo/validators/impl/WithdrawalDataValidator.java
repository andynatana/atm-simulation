package com.example.demo.validators.impl;

import com.example.demo.entity.Account;
import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.validators.OperationDataValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawalDataValidator implements OperationDataValidator {

    @Override
    public void validate(BigDecimal amount, Account account) throws BalanceInsufficientException {
        if (amount.compareTo(account.getBalance()) > 0) throw new BalanceInsufficientException();
    }
}

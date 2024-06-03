package com.example.demo.builder.impl;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.validators.OperationDataValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawOperationBuilder implements OperationBuilder {

    private final OperationDataValidator operationDataValidator;

    public WithdrawOperationBuilder(OperationDataValidator operationDataValidator) {
        this.operationDataValidator = operationDataValidator;
    }

    @Override
    public void validateData(BigDecimal amount, Account account) throws OperationException {
        operationDataValidator.validate(amount, account);
    }

    @Override
    public OperationType getType() {
        return OperationType.WITHDRAW;
    }
}

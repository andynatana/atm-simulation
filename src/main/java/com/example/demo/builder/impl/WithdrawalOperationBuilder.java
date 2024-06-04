package com.example.demo.builder.impl;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.utils.OperationReferenceGenerator;
import com.example.demo.validators.OperationDataValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.demo.entity.enums.OperationType.WITHDRAW;

@Component
@Qualifier("withdrawalOperationBuilder")
public class WithdrawalOperationBuilder implements OperationBuilder {

    private final OperationDataValidator operationDataValidator;

    public WithdrawalOperationBuilder(OperationDataValidator operationDataValidator) {
        this.operationDataValidator = operationDataValidator;
    }

    @Override
    public String generateReference() {
        return OperationReferenceGenerator.generateOne(WITHDRAW);
    }

    @Override
    public void validateData(BigDecimal amount, Account account) throws OperationException {
        operationDataValidator.validate(amount, account);
    }

    @Override
    public OperationType getType() {
        return WITHDRAW;
    }
}

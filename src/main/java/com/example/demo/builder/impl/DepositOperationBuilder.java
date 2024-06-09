package com.example.demo.builder.impl;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.utils.OperationReferenceGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.example.demo.entity.enums.OperationType.DEPOSIT;

@Component
@Qualifier("depositOperationBuilder")
public class DepositOperationBuilder implements OperationBuilder {

    @Override
    public String generateReference() {
        return OperationReferenceGenerator.generateOne(DEPOSIT);
    }

    @Override
    public OperationType getType() {
        return OperationType.DEPOSIT;
    }
}

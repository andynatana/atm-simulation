package com.example.demo.builder.impl;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.enums.OperationType;
import org.springframework.stereotype.Service;

@Service
public class WithdrawOperationBuilder implements OperationBuilder {

    @Override
    public OperationType getType() {
        return OperationType.WITHDRAW;
    }
}

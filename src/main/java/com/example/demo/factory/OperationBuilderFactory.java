package com.example.demo.factory;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.builder.impl.DepositOperationBuilder;
import com.example.demo.builder.impl.WithdrawalOperationBuilder;
import com.example.demo.entity.enums.OperationType;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OperationBuilderFactory {

    private ApplicationContext applicationContext;

    public OperationBuilder getInstance(OperationType operationType) {
        switch (operationType) {
            case WITHDRAW -> {
                return applicationContext.getBean(WithdrawalOperationBuilder.class);
            }
            case DEPOSIT -> {
                return applicationContext.getBean(DepositOperationBuilder.class);
            }
            default -> throw new IllegalArgumentException("No bean found for the operation " + operationType);
        }
    }
}

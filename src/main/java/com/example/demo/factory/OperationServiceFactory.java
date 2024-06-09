package com.example.demo.factory;

import com.example.demo.entity.enums.OperationType;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import com.example.demo.service.impl.operation.DepositService;
import com.example.demo.service.impl.operation.WithdrawalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OperationServiceFactory {

    private final OperationRepository operationRepository;
    private final AccountService accountService;

    public OperationService getInstance(OperationType operationType) {
        switch (operationType) {
            case WITHDRAW -> {
                return new WithdrawalService(accountService, operationRepository);
            }
            case DEPOSIT -> {
                return new DepositService(operationRepository);
            }
            default -> throw new IllegalArgumentException("No bean found for the operation " + operationType);
        }
    }
}

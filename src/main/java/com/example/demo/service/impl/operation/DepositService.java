package com.example.demo.service.impl.operation;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("depositService")
@AllArgsConstructor
public class DepositService implements OperationService {

    private OperationRepository operationRepository;

    @Override
    public void persistData(Operation operation) {
        Account account = operation.getAccount();
        account.setBalance(account.getBalance().add(operation.getAmount()));
        operationRepository.save(operation);
    }
}

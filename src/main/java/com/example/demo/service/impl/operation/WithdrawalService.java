package com.example.demo.service.impl.operation;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WithdrawalService implements OperationService {

    private final AccountService accountService;
    private final OperationRepository operationRepository;

    @Override
    public void persistData(Operation operation) {

        //update account balance
        Account account = operation.getAccount();
        account.setBalance(account.getBalance().subtract(operation.getAmount()));
        accountService.save(account);

        //create operation of type withdraw
        operationRepository.save(operation);

    }
}

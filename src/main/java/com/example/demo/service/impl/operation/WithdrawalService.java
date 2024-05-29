package com.example.demo.service.impl.operation;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WithdrawalService implements OperationService {

    private final AccountService accountService;
    private final OperationBuilder operationBuilder;
    private final OperationRepository operationRepository;

    @Override
    public void persistData(BigDecimal amount, Account account, Operation operation) {

        //create a transaction of type withdraw
        operationRepository.save(operation);

        //update account balance
        account.setBalance(account.getBalance().subtract(amount));
        accountService.save(account);
    }


    @Override
    public Operation createOperationInstance(BigDecimal amount, Account account) {
        //create transaction reference and the object transaction
        return operationBuilder.build(amount, account);
    }
}

package com.example.demo.service.impl.operation;

import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Qualifier("withdrawalService")
public class WithdrawalService implements OperationService {

    private final AccountService accountService;
    private final OperationRepository operationRepository;

    @Override
    public void updateAccount(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
        accountService.save(account);
    }

    @Override
    public void persistData(Operation operation) {
        operationRepository.save(operation);

    }
}

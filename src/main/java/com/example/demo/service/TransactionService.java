package com.example.demo.service;

import com.example.demo.builder.TransactionBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionService {

    private final AccountService accountService;
    private final TransactionBuilder transactionBuilder;
    private final TransactionRepository transactionRepository;

    @Transactional
    public String process(BigDecimal amount, String accountNumber) throws BalanceInsufficientException {
        //find the current account
        Account account = accountService.findByAccountNumber(accountNumber);

        //check if balance is sufficient
        if (amount.compareTo(account.getBalance()) > 0) throw new BalanceInsufficientException();

        //create transaction reference and the object transaction
        Transaction transaction = transactionBuilder.build(amount, account);

        //create a transaction of type withdraw
        transactionRepository.save(transaction);


        //update account balance
        account.setBalance(account.getBalance().subtract(amount));
        accountService.save(account);
        return null;
    }
}

package com.example.demo.builder.impl;

import com.example.demo.builder.TransactionBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.enums.TransactionType;
import com.example.demo.utils.TransactionReferenceGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawTransactionBuilder implements TransactionBuilder {

    @Override
    public Transaction build(BigDecimal amount, Account account) {
        String transactionReference = TransactionReferenceGenerator.generateOne();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReference(transactionReference);
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setUser(account.getOwner());
        return transaction;
    }
}

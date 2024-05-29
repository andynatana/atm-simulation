package com.example.demo.builder;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;

import java.math.BigDecimal;

public interface TransactionBuilder {

    Transaction build(BigDecimal amount, Account account);
}

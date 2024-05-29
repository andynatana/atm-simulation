package com.example.demo.mapper;

import com.example.demo.entity.Account;
import com.example.demo.pojo.ws.response.AccountDTO;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public AccountDTO mapToAccountDTO(Account account) {
        return new AccountDTO(
                account.getAccountNumber(),
                account.getBalance(),
                account.getAccountCategory().getName(),
                account.getOwner().getName()
        );
    }
}

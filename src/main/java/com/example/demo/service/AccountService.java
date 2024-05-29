package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountCategory;
import com.example.demo.entity.User;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.ws.params.AccountCreationParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final AccountCategoryService accountCategoryService;

    public Account getBalance(Long userId, Long accountCategoryId) {
        User user = userService.findById(userId);
        AccountCategory accountCategory = accountCategoryService.findById(accountCategoryId);
        return accountRepository.findByOwnerAndAccountCategory(user, accountCategory)
                .orElseThrow(() -> new AccountNotFoundException("The user does not have " + accountCategory.getName() + " account"));
    }

    public Account create(AccountCreationParam accountCreationParam) {
        Account account = createAccountInstanceFromParam(accountCreationParam);
        return accountRepository.save(account);
    }

    private Account createAccountInstanceFromParam(AccountCreationParam accountCreationParam) {
        AccountCategory accountCategory = accountCategoryService.findById(accountCreationParam.accountCategoryId());
        User user = userService.findById(accountCreationParam.userId());
        Account account = new Account();
        account.setAccountCategory(accountCategory);
        account.setOwner(user);
        account.setAccountNumber(accountCreationParam.accountNumber());
        account.setBalance(accountCreationParam.balance());
        return account;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}

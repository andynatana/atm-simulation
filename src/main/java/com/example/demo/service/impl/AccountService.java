package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.entity.AccountCategory;
import com.example.demo.entity.User;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.pojo.ws.response.AccountDTO;
import com.example.demo.repository.AccountRepository;
import com.example.demo.pojo.ws.params.AccountCreationParam;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final AccountCategoryService accountCategoryService;
    private final AccountMapper accountMapper;

    public AccountDTO getBalance(Long userId, Long accountCategoryId) {
        User user = userService.findById(userId);
        AccountCategory accountCategory = accountCategoryService.findById(accountCategoryId);
        return accountRepository.findByOwnerAndAccountCategory(user, accountCategory)
                .map(accountMapper::mapToAccountDTO)
                .orElseThrow(() -> new AccountNotFoundException("The user does not have " + accountCategory.getName() + " account"));
    }

    public AccountDTO getBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountMapper::mapToAccountDTO)
                .orElseThrow(() -> new AccountNotFoundException("The user does not have any account"));
    }

    @Transactional
    public Account create(AccountCreationParam accountCreationParam) {
        Account account = createAccountInstanceFromParam(accountCreationParam);
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Transactional
    public void save(Account account) {
        accountRepository.save(account);
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

}

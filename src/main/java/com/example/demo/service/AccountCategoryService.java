package com.example.demo.service;

import com.example.demo.entity.AccountCategory;
import com.example.demo.exception.AccountCategoryNotFoundException;
import com.example.demo.repository.AccountCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountCategoryService {

    private final AccountCategoryRepository accountCategoryRepository;

    public AccountCategory findById(Long id) {
        return accountCategoryRepository.findById(id)
                .orElseThrow(() -> new AccountCategoryNotFoundException("Account Category with id "+id+" not found"));
    }

    public AccountCategory save(AccountCategory accountCategory) {
        log.info("Saving new ACCOUNT CATEGORY {}", accountCategory);
        AccountCategory category = accountCategoryRepository.save(accountCategory);
        log.info("Successfully saved in the database");
        return category;
    }

    public List<AccountCategory> findAll() {
        return accountCategoryRepository.findAll();
    }
}

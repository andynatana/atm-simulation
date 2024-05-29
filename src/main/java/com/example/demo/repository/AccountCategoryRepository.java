package com.example.demo.repository;

import com.example.demo.entity.AccountCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCategoryRepository extends JpaRepository<AccountCategory, Long> {
}

package com.example.demo.exception;

public class AccountCategoryNotFoundException extends RuntimeException {

    public AccountCategoryNotFoundException(String message) {
        super(message);
    }
}

package com.example.demo.exception;

public class BalanceInsufficientException extends Exception {

    public BalanceInsufficientException() {
        super("Balance insufficient");
    }
}

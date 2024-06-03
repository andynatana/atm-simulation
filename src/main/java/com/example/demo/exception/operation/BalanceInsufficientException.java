package com.example.demo.exception.operation;

public class BalanceInsufficientException extends OperationException {

    public BalanceInsufficientException() {
        super("Balance insufficient");
    }
}

package com.example.demo.controllers.advice;

import com.example.demo.exception.AccountCategoryNotFoundException;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.operation.BalanceInsufficientException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.pojo.ws.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse wsResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(wsResponse);
    }

    @ExceptionHandler(AccountCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountCategoryNotFoundException(AccountCategoryNotFoundException exception) {
        ErrorResponse wsResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(wsResponse);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException exception) {
        ErrorResponse wsResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(wsResponse);
    }

    @ExceptionHandler(BalanceInsufficientException.class)
    public ResponseEntity<ErrorResponse> handleBalanceInsufficientException(BalanceInsufficientException exception) {
        ErrorResponse wsResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(wsResponse);
    }
}

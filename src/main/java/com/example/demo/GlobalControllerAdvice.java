package com.example.demo;

import com.example.demo.exception.AccountCategoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.pojo.ErrorResponse;
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
}

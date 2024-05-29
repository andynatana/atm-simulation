package com.example.demo.controllers;

import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.pojo.ws.params.WithdrawParam;
import com.example.demo.pojo.ws.response.WithdrawResponse;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/withdraw")
@AllArgsConstructor
public class WithdrawController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<WithdrawResponse> withdraw(@RequestBody WithdrawParam withdrawParam) throws BalanceInsufficientException {
        String transactionId = transactionService.process(withdrawParam.amount(), withdrawParam.accountNumber());
        return ResponseEntity.ok(new WithdrawResponse(transactionId));
    }
}

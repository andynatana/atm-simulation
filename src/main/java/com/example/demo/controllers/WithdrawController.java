package com.example.demo.controllers;

import com.example.demo.entity.Account;
import com.example.demo.exception.BalanceInsufficientException;
import com.example.demo.pojo.ws.params.WithdrawParam;
import com.example.demo.pojo.ws.response.WithdrawResponse;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import com.example.demo.validators.OperationDataValidator;
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

    private final OperationService operationService;
    private final AccountService accountService;
    private final OperationDataValidator operationDataValidator;

    @PostMapping
    public ResponseEntity<WithdrawResponse> withdraw(@RequestBody WithdrawParam withdrawParam) throws BalanceInsufficientException {
        //finding the current account
        Account account = accountService.findByAccountNumber(withdrawParam.accountNumber());

        //processing operation : here withdrawal
        operationDataValidator.validate(withdrawParam.amount(), account);
        String transactionId = operationService.process(withdrawParam.amount(), account);
        return ResponseEntity.ok(new WithdrawResponse(transactionId));
    }
}

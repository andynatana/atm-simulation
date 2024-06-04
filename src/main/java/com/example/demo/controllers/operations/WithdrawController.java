package com.example.demo.controllers.operations;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.pojo.ws.params.OperationParam;
import com.example.demo.pojo.ws.response.WithdrawResponse;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

    private final OperationService withdrawalService;
    private final OperationBuilder withdrawalOperationBuilder;
    private final AccountService accountService;

    public WithdrawController(
            @Qualifier("withdrawalService") OperationService withdrawalService,
            @Qualifier("withdrawalOperationBuilder") OperationBuilder withdrawalOperationBuilder,
            AccountService accountService) {
        this.withdrawalService = withdrawalService;
        this.accountService = accountService;
        this.withdrawalOperationBuilder = withdrawalOperationBuilder;
    }

    @PostMapping
    public ResponseEntity<WithdrawResponse> withdraw(@RequestBody OperationParam operationParam) throws OperationException {
        //finding the current account
        Account account = accountService.findByAccountNumber(operationParam.accountNumber());

        //build the current operation
        Operation operation = withdrawalOperationBuilder.build(operationParam.amount(), account);

        //processing operation
        String transactionId = withdrawalService.process(operation);
        return ResponseEntity.ok(new WithdrawResponse(transactionId));
    }
}

package com.example.demo.controllers.operations;


import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.pojo.ws.params.OperationParam;
import com.example.demo.pojo.ws.response.DepositResponse;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final OperationService operationService;
    private final OperationBuilder operationBuilder;
    private final AccountService accountService;

    @Autowired
    public DepositController(
            @Qualifier("depositService") OperationService depositService,
            @Qualifier("depositOperationBuilder") OperationBuilder operationBuilder,
            AccountService accountService
    ) {
        this.operationService = depositService;
        this.accountService = accountService;
        this.operationBuilder = operationBuilder;
    }

    @PostMapping
    public ResponseEntity<DepositResponse> deposit(@RequestBody OperationParam operationParam) throws OperationException {
        //finding the current account
        Account account = accountService.findByAccountNumber(operationParam.accountNumber());

        //building operation instance
        Operation operation = operationBuilder.build(operationParam.amount(), account);

        //process deposit
        String reference = operationService.process(operation);

        //return response
        DepositResponse depositResponse = new DepositResponse(account.getOwner().getName(), operationParam.amount(), reference);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(depositResponse);
    }
}

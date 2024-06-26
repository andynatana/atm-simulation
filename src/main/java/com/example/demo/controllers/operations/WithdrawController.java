package com.example.demo.controllers.operations;

import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.factory.OperationBuilderFactory;
import com.example.demo.factory.OperationServiceFactory;
import com.example.demo.pojo.ws.params.OperationParam;
import com.example.demo.pojo.ws.response.WithdrawResponse;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
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

    private static final OperationType OPERATION_TYPE = OperationType.WITHDRAW;

    private final OperationBuilderFactory operationBuilderFactory;
    private final OperationServiceFactory operationServiceFactory;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<WithdrawResponse> withdraw(@RequestBody OperationParam operationParam) throws OperationException {
        //finding the current account
        Account account = accountService.findByAccountNumber(operationParam.accountNumber());

        //build the current operation
        OperationBuilder operationBuilder = operationBuilderFactory.getInstance(OPERATION_TYPE);
        Operation operation = operationBuilder.build(operationParam.amount(), account);

        //processing operation
        OperationService withdrawService = operationServiceFactory.getInstance(OPERATION_TYPE);
        String transactionId = withdrawService.process(operation);
        return ResponseEntity.ok(new WithdrawResponse(transactionId));
    }
}

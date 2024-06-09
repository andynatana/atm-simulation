package com.example.demo.controllers.operations;


import com.example.demo.builder.OperationBuilder;
import com.example.demo.entity.Account;
import com.example.demo.entity.Operation;
import com.example.demo.entity.enums.OperationType;
import com.example.demo.exception.operation.OperationException;
import com.example.demo.factory.OperationBuilderFactory;
import com.example.demo.factory.OperationServiceFactory;
import com.example.demo.pojo.ws.params.OperationParam;
import com.example.demo.pojo.ws.response.DepositResponse;
import com.example.demo.service.OperationService;
import com.example.demo.service.impl.AccountService;
import com.example.demo.validators.OperationDataValidator;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DepositController {

    private static final OperationType OPERATION_TYPE = OperationType.DEPOSIT;

    private final OperationServiceFactory operationServiceFactory;
    private final OperationBuilderFactory operationBuilderFactory;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<DepositResponse> deposit(@RequestBody OperationParam operationParam) throws OperationException {
        //finding the current account
        Account account = accountService.findByAccountNumber(operationParam.accountNumber());

        //building operation instance
        OperationBuilder operationBuilder = operationBuilderFactory.getInstance(OPERATION_TYPE);
        Operation operation = operationBuilder.build(operationParam.amount(), account);

        //process deposit
        OperationService depositService = operationServiceFactory.getInstance(OPERATION_TYPE);
        String reference = depositService.process(operation);

        //return response
        DepositResponse depositResponse = new DepositResponse(account.getOwner().getName(), operationParam.amount(), reference);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(depositResponse);
    }
}

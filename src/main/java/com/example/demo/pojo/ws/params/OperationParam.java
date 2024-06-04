package com.example.demo.pojo.ws.params;

import com.example.demo.entity.enums.OperationType;

import java.math.BigDecimal;

public record OperationParam(BigDecimal amount, String accountNumber, OperationType operationType) {
}

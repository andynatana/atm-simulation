package com.example.demo.pojo.ws.params;

import java.math.BigDecimal;

public record OperationParam(BigDecimal amount, String accountNumber) {
}

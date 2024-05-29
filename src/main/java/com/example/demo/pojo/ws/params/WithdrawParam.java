package com.example.demo.pojo.ws.params;

import java.math.BigDecimal;

public record WithdrawParam(BigDecimal amount, String accountNumber) {
}

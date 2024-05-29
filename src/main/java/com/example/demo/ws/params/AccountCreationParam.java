package com.example.demo.ws.params;

import java.math.BigDecimal;

public record AccountCreationParam(String accountNumber, BigDecimal balance, Long accountCategoryId, Long userId) {

}

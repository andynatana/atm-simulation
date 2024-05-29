package com.example.demo.pojo.ws.response;

import java.math.BigDecimal;

public record AccountDTO(String accountNumber, BigDecimal balance, String accountCategory, String owner) {
}

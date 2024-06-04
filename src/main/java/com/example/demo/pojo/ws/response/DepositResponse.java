package com.example.demo.pojo.ws.response;

import java.math.BigDecimal;

public record DepositResponse(String ownerName, BigDecimal depositAmount, String reference) {
}

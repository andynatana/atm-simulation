package com.example.demo.entity.enums;

import lombok.Getter;

@Getter
public enum OperationType {
    WITHDRAW("WD"),
    DEPOSIT("DP"),
    TRANSFER("TF");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

}

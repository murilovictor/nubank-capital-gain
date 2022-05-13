package com.nubank.enumeration;

public enum OperationType {

    BUY("buy"), SELL("sell");

    private final String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}

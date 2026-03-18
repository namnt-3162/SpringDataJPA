package com.example.demo;

public class TransferResponse {
    private String message;
    private Double fromAccountBalance;
    private Double toAccountBalance;

    public TransferResponse(String message, Double fromAccountBalance, Double toAccountBalance) {
        this.message = message;
        this.fromAccountBalance = fromAccountBalance;
        this.toAccountBalance = toAccountBalance;
    }
    
    public String getMessage() { return message; }
    public Double getFromAccountBalance() { return fromAccountBalance; }
    public Double getToAccountBalance() { return toAccountBalance; }
}

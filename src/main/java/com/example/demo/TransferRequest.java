package com.example.demo;
public class TransferRequest {
    private Long fromId;
    private Long toId;
    private Double amount;

    public TransferRequest() {}

    public Long getFromId() { return fromId; }
    public void setFromId(Long fromId) { this.fromId = fromId; }
    
    public Long getToId() { return toId; }
    public void setToId(Long toId) { this.toId = toId; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
package com.lastminute.core.entity;

public class FlightResult {

    private String code;
    private double amount;
    private String currency;

    public FlightResult(String code, double amount, String currency) {
        this.code = code;
        this.amount = amount;
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

}

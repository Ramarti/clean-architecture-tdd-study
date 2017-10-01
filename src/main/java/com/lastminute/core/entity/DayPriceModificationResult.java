package com.lastminute.core.entity;

public class DayPriceModificationResult {

    private double result;
    private double modifier;

    public DayPriceModificationResult(double result, double modifier) {
        this.result = result;
        this.modifier = modifier;
    }

    public double getResult() {
        return result;
    }

    public double getModifier() {
        return modifier;
    }
}

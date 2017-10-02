package com.lastminute.core.entity;

/**
 * Effects of applying day modifier
 * Passing the modifier helps make an informative output
 */
public class DayPriceModificationResult {


    private double result;
    private double modifier;

    public DayPriceModificationResult(double result, double modifier) {
        this.result = result;
        this.modifier = modifier;
    }

    /**
     * get new price
     * @return
     */
    public double getResult() {
        return result;
    }

    /**
     * get modifier applied
     * @return
     */
    public double getModifier() {
        return modifier;
    }
}

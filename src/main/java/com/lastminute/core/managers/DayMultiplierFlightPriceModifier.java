package com.lastminute.core.managers;

public class DayMultiplierFlightPriceModifier implements DayPriceModifier {
    /*
        | days prior to the departure date | % of the base price |
        |----------------------------------|---------------------|
        | more than 30 (i.e. >= 31)        | 80%                 |
        | 30 - 16                          | 100%                |
        | 15 - 3                           | 120%                |
        | less that 3 (i.e. <= 2)          | 150%                |
         */

    @Override
    public double modifyPrice(double rawPrice, int days) {
        if (days < 0) {
            throw new IllegalArgumentException("days should be positive");
        }
        if (days <= 2) {
            return  rawPrice * 1.5;
        } else if (days >= 3 && days <= 15) {
            return  rawPrice * 1.2;
        } else if (days >= 16 && days <= 30) {
            return rawPrice;
        } else {
            return  rawPrice * 0.8;
        }
    }
}

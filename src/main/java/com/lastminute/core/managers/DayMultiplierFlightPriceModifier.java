package com.lastminute.core.managers;

import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.usecase.searchflight.DayPriceModifier;

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
    public DayPriceModificationResult modifyPrice(double rawPrice, int days) {
        double modifier = 1;
        if (days < 0) {
            throw new IllegalArgumentException("days should be positive");
        }
        if (days <= 2) {
            modifier = 1.5;
        } else if (days >= 3 && days <= 15) {
            modifier = 1.2;
        } else if (days >= 16 && days <= 30) {
            modifier = 1.0;
        } else {
            modifier = 0.8;
        }
        return new DayPriceModificationResult(rawPrice * modifier,modifier);
    }
}

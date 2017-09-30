package com.lastminute.core.entity;

public class DayMultiplierFlightPriceModifier implements FlightPriceModifier {
    /*
        | days prior to the departure date | % of the base price |
        |----------------------------------|---------------------|
        | more than 30 (i.e. >= 31)        | 80%                 |
        | 30 - 16                          | 100%                |
        | 15 - 3                           | 120%                |
        | less that 3 (i.e. <= 2)          | 150%                |
         */
    @Override
    public Double modify(Double rawprice, PriceParameters parameter) {
        int days = parameter.getDaysPriorToDeparture();
        if (days <= 2) {
            return  rawprice * 1.5;
        } else if (days >= 3 && days <= 15) {
            return  rawprice * 1.2;
        } else if (days >= 16 && days <= 30) {
            return rawprice;
        } else {
            return  rawprice * 0.8;
        }
    }
}

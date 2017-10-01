package com.lastminute.core.managers;

public interface DayPriceModifier {

    public double modifyPrice(double rawPrice, int days);
}

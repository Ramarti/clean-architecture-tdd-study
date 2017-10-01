package com.lastminute.core.usecase.searchflight;

public interface DayPriceModifier {

    public double modifyPrice(double rawPrice, int days);
}

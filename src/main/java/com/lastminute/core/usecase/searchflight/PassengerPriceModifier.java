package com.lastminute.core.usecase.searchflight;

public interface PassengerPriceModifier {

    public double modifyPrice(double rawPrice, int passengers);

}

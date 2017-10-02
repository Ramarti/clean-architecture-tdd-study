package com.lastminute.core.usecase.searchflight;

/**
 * Interface for business rule ruling prices and passenger number.
 * Having this separation is interesting if business rules change
 */
public interface PassengerPriceModifier {

    public double modifyPrice(double rawPrice, int passengers);

}

package com.lastminute.core.managers;

import com.lastminute.core.usecase.searchflight.PassengerPriceModifier;

/**
 * Passenger number business rule. Just a multiplication.
 */
public class MultiplePassengerFlightPriceModifier implements PassengerPriceModifier{

    @Override
    public double modifyPrice(double rawPrice, int passengers) {
        if (passengers <= 0) {
            throw new IllegalArgumentException("Passengers "+passengers+"should be 1 or more");
        }
        return rawPrice * passengers;
    }
}

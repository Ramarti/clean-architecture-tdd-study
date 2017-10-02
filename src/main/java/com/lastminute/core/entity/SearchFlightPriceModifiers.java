package com.lastminute.core.entity;

import com.lastminute.core.usecase.searchflight.DayPriceModifier;
import com.lastminute.core.usecase.searchflight.PassengerPriceModifier;

/**
 *  Grouping for useCase constructor
 */
public class SearchFlightPriceModifiers {

    private DayPriceModifier dayPriceModifier;
    private PassengerPriceModifier passengerPriceModifier;

    public SearchFlightPriceModifiers(DayPriceModifier dayPriceModifier, PassengerPriceModifier passengerPriceModifier) {
        this.dayPriceModifier = dayPriceModifier;
        this.passengerPriceModifier = passengerPriceModifier;
    }

    public DayPriceModifier getDayPriceModifier() {
        return dayPriceModifier;
    }

    public PassengerPriceModifier getPassengerPriceModifier() {
        return passengerPriceModifier;
    }

}

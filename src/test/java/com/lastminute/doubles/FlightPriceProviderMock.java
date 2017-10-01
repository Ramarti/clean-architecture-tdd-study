package com.lastminute.doubles;

import com.lastminute.dataproviders.flightprices.FlightPriceProvider;

public class FlightPriceProviderMock implements FlightPriceProvider {

    public boolean wasPriceCalled = false;
    public Double priceFound;

    @Override
    public Double getPriceForFlight(String code) {
        wasPriceCalled = true;
        return priceFound;
    }
}

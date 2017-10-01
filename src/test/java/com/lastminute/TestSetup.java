package com.lastminute;

import com.lastminute.dataproviders.flightprices.FlightPriceProvider;
import com.lastminute.doubles.FlightPriceProviderMock;
import com.lastminute.doubles.FlightRouteProviderMock;

public class TestSetup {

    public static void setupContext() {
        Context.flightPriceProvider = new FlightPriceProviderMock();
        Context.flightRoutesProvider = new FlightRouteProviderMock();
    }

    public static FlightPriceProviderMock getMockPriceProvider() {
        return (FlightPriceProviderMock) Context.flightPriceProvider;
    }

    public static FlightRouteProviderMock getMockRoutesProvider() {
        return (FlightRouteProviderMock) Context.flightRoutesProvider;
    }
}

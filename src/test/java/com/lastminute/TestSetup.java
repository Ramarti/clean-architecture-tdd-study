package com.lastminute;

import com.lastminute.dataproviders.flightprices.FlightPriceProvider;
import com.lastminute.doubles.FlightPriceProviderMock;
import com.lastminute.doubles.FlightRouteProviderMock;

public class TestSetup {

    public static void setupContext() {
        Context.getInstance().flightPriceProvider = new FlightPriceProviderMock();
        Context.getInstance().flightRoutesProvider = new FlightRouteProviderMock();
    }

    public static FlightPriceProviderMock getMockPriceProvider() {
        return (FlightPriceProviderMock) Context.getInstance().flightPriceProvider;
    }

    public static FlightRouteProviderMock getMockRoutesProvider() {
        return (FlightRouteProviderMock) Context.getInstance().flightRoutesProvider;
    }
}

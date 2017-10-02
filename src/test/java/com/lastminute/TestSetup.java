package com.lastminute;

import com.lastminute.core.controller.Controller;
import com.lastminute.core.entity.FlightResult;
import com.lastminute.core.usecase.searchflight.SearchFlightResponse;
import com.lastminute.dataproviders.RecordReaderFactory;
import com.lastminute.dataproviders.flightprices.FlightPriceProvider;
import com.lastminute.doubles.FlightPriceProviderMock;
import com.lastminute.doubles.FlightRouteProviderMock;
import com.lastminute.doubles.MockDayPriceModifier;

import java.util.List;

public class TestSetup {

    public static void setupContext() {
        Context.getInstance().flightPriceProvider = new FlightPriceProviderMock();
        Context.getInstance().flightRoutesProvider = new FlightRouteProviderMock();
        Context.getInstance().recordReaderFactory = new RecordReaderFactory();
    }

    public static FlightPriceProviderMock getMockPriceProvider() {
        return (FlightPriceProviderMock) Context.getInstance().flightPriceProvider;
    }

    public static FlightRouteProviderMock getMockRoutesProvider() {
        return (FlightRouteProviderMock) Context.getInstance().flightRoutesProvider;
    }


}

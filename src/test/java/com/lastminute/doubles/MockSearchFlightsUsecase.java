package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.SearchFlightRequest;
import com.lastminute.core.usecase.searchflight.SearchFlightsInputBoundary;
import com.lastminute.core.usecase.searchflight.SearchFlightsOutputBoundary;

public class MockSearchFlightsUsecase implements SearchFlightsInputBoundary {
    boolean searchFlightsCalled = false;

    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {
        searchFlightsCalled = true;
    }

    public boolean isSearchFlightsCalled() {
        return searchFlightsCalled;
    }

}

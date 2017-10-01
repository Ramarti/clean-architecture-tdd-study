package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.SearchFlightResponse;
import com.lastminute.core.usecase.searchflight.SearchFlightsOutputBoundary;

public class SearchFlightsOutputBoundarySpy implements SearchFlightsOutputBoundary {

    public SearchFlightResponse response;
    public boolean called = false;
    @Override
    public void present(SearchFlightResponse response) {
        called = true;
        this.response = response;
    }
}

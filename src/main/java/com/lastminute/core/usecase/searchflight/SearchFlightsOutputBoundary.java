package com.lastminute.core.usecase.searchflight;

public interface SearchFlightsOutputBoundary {

    SearchFlightViewModel getViewModel();
    void present(SearchFlightResponse response);
}

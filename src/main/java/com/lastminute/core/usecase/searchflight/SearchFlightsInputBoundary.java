package com.lastminute.core.usecase.searchflight;

import java.util.List;

/**
 * Input to Use Case interactor
 */
public interface SearchFlightsInputBoundary {
    void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter);
}

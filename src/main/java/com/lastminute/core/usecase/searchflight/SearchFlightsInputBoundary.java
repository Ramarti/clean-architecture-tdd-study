package com.lastminute.core.usecase.searchflight;

import java.util.List;

public interface SearchFlightsInputBoundary {
    void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter);
}

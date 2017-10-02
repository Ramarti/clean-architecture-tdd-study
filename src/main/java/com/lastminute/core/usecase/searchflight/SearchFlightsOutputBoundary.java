package com.lastminute.core.usecase.searchflight;

/**
 * Output to usecase interactor
 */
public interface SearchFlightsOutputBoundary {

    SearchFlightViewModel getViewModel();
    void present(SearchFlightResponse response);
}

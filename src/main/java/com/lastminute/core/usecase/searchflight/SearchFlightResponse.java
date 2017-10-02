package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.FlightResult;

import java.util.List;

/**
 * Results of the SearchFlightUseCase
 * Includes request that triggered it
 */
public class SearchFlightResponse {

    private List<FlightResult> results;
    private SearchFlightRequest request;

    public SearchFlightResponse(List<FlightResult> results,SearchFlightRequest request) {
        this.results = results;
        this.request = request;
    }

    public List<FlightResult> getResults() {
        return results;
    }

    public SearchFlightRequest getRequest() {
        return request;
    }
}


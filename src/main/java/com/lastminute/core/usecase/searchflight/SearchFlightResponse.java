package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.FlightResult;

import java.util.List;

public class SearchFlightResponse {

    private List<FlightResult> results;
    private SearchFlightRequest request;

    public SearchFlightResponse(List<FlightResult> results) {
        this.results = results;
    }

    public List<FlightResult> getResults() {
        return results;
    }
}


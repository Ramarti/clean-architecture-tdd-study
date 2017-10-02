package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.SearchFlightResponse;
import com.lastminute.core.usecase.searchflight.SearchFlightViewModel;
import com.lastminute.core.usecase.searchflight.SearchFlightsOutputBoundary;

public class SearchFlightsOutputBoundarySpy implements SearchFlightsOutputBoundary {

    public SearchFlightResponse response;
    public boolean called = false;

    private SearchFlightViewModel viewModel;
    @Override
    public SearchFlightViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void present(SearchFlightResponse response) {
        called = true;
        this.response = response;
    }
}

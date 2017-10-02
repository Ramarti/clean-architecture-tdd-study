package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.SearchFlightResponse;
import com.lastminute.core.usecase.searchflight.SearchFlightViewModel;
import com.lastminute.core.usecase.searchflight.SearchFlightsOutputBoundary;

public class MockSearchFlightPresenter implements SearchFlightsOutputBoundary {

    boolean presentCalled = false;
    boolean getViewModelCalled = false;

    @Override
    public SearchFlightViewModel getViewModel() {
        getViewModelCalled = true;
        return null;
    }

    @Override
    public void present(SearchFlightResponse response) {
        presentCalled = true;
    }

    public boolean isPresentCalled() {
        return presentCalled;
    }

    public boolean isGetViewModelCalled() {
        return getViewModelCalled;
    }
}

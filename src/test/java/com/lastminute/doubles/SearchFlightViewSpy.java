package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.SearchFlightView;
import com.lastminute.core.usecase.searchflight.SearchFlightViewModel;

public class SearchFlightViewSpy implements SearchFlightView {

    private boolean viewable = false;
    public boolean wasMadeViewable() {
        return viewable;
    }

    @Override
    public void generateView(SearchFlightViewModel viewModel) {
        viewable = true;
    }
}

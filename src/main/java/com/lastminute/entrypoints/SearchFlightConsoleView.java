package com.lastminute.entrypoints;

import com.lastminute.core.usecase.searchflight.SearchFlightView;
import com.lastminute.core.usecase.searchflight.SearchFlightViewModel;

import java.io.PrintStream;

public class SearchFlightConsoleView implements SearchFlightView {

    private PrintStream stream;

    public SearchFlightConsoleView(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void generateView(SearchFlightViewModel viewModel) {
        stream.println(viewModel.getPrompt());
    }
}

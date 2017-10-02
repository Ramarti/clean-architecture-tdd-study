package com.lastminute.entrypoints;

import com.lastminute.core.usecase.searchflight.SearchFlightView;
import com.lastminute.core.usecase.searchflight.SearchFlightViewModel;

import java.io.PrintStream;

/**
 * View implementation. I used a stream so we could reuse this for tests or a CLI app deppending on what we want
 */
public class SearchFlightPrintStreamView implements SearchFlightView {

    private PrintStream stream;

    public SearchFlightPrintStreamView(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void generateView(SearchFlightViewModel viewModel) {
        stream.print(viewModel.getPrompt());
    }
}

package com.lastminute.core.usecase.searchflight;

public class SearchFlightViewModel {

    private String prompt;

    public SearchFlightViewModel(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}

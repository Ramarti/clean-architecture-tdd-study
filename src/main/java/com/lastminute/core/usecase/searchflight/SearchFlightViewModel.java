package com.lastminute.core.usecase.searchflight;

/**
 * A model of what the response will look on the view. In this case a string
 */
public class SearchFlightViewModel {

    private String prompt;

    public SearchFlightViewModel(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}

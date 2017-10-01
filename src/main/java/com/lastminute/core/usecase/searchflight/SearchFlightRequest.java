package com.lastminute.core.usecase.searchflight;

public class SearchFlightRequest {

    private String origin;
    private String destination;
    private int daysUntilDeparture;

    public SearchFlightRequest(String origin, String destination, int daysUntilDeparture) {
        this.origin = origin;
        this.destination = destination;
        this.daysUntilDeparture = daysUntilDeparture;
    }




    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getDaysUntilDeparture() {
        return daysUntilDeparture;
    }

}
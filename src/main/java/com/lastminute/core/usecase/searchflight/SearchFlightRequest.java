package com.lastminute.core.usecase.searchflight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFlightRequest {

    private String origin;
    private String destination;
    private int daysUntilDeparture;
    private int passengerNumber;

    //TODO builder
    public SearchFlightRequest(String origin, String destination, int daysUntilDeparture,int passengerNumber) {
        this.origin = origin;
        this.destination = destination;
        this.daysUntilDeparture = daysUntilDeparture;
        this.passengerNumber = passengerNumber;
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

    public int getPassengerNumber() {
        return passengerNumber;
    }
}
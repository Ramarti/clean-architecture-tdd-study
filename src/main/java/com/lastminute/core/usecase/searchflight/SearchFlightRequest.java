package com.lastminute.core.usecase.searchflight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFlightRequest {

    private String origin;
    private String destination;
    private int daysUntilDeparture;
    private int passengerNumber;

    //TODO builder
    public SearchFlightRequest(String origin, String destination, int daysUntilDeparture,int passengerNumber) throws SearchFlightRequestException {
        this.origin = origin;
        this.destination = destination;
        this.daysUntilDeparture = daysUntilDeparture;
        this.passengerNumber = passengerNumber;
    }

    private static void checkAirportFormatting(String fieldValue) throws SearchFlightRequestException {
        boolean isCorrect = Pattern.compile("[A-Z]+").matcher(fieldValue).matches();
        if (!isCorrect) {
            throw new SearchFlightRequestException("Wrong input: "+fieldValue);
        }

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
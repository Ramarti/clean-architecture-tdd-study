package com.lastminute.core.usecase.searchflight;

import java.util.regex.Pattern;

public class SearchFlightRequest {

    private String origin;
    private String destination;
    private int daysUntilDeparture;
    private int passengerNumber;

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

    void validate() throws SearchFlightRequestException {
        validateFlight(origin);
        validateFlight(destination);
        validateDaysDeparture();
        validatePassengers();
    }

    private void validateFlight(String airport) throws SearchFlightRequestException {
        String message = "Wrong airport ";
        if(airport == null) {
            throw new SearchFlightRequestException(message+"null");
        }
        if(!Pattern.matches("^[A-Z]+$",airport)) {
            throw new SearchFlightRequestException(message+airport);
        }
    }

    private void validateDaysDeparture() throws SearchFlightRequestException {
        if (daysUntilDeparture < 0) {
            throw new SearchFlightRequestException("daysUntilDeparture must be >= 0");
        }
    }

    private void validatePassengers() throws SearchFlightRequestException {
        if (passengerNumber <= 0) {
            throw new SearchFlightRequestException("daysUntilDeparture must be > 0");
        }
    }
}
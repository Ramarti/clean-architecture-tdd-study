package com.lastminute.core.usecase.searchflight;

import java.util.regex.Pattern;

/**
 * Representation of user input for search
 */
public class SearchFlightRequest {

    private String origin;
    private String destination;
    private int daysUntilDeparture;
    private int passengerNumber;

    /**
     * Constructor of search input.
     * One parameter more and I will start grouping then in other POJOS
     * I didn't make a Builder since all the parameters are mandatory
     * @param origin
     * @param destination
     * @param daysUntilDeparture
     * @param passengerNumber
     */
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

    /**
     * Validate user input
     * The exercise didn't have explicit rules so I included some basic ones
     * @throws SearchFlightRequestException
     */
    void validate() throws SearchFlightRequestException {
        validateFlight(origin);
        validateFlight(destination);
        validateDaysDeparture();
        validatePassengers();
    }

    /**
     * Validate the airport is uppercase letters only
     * @param airport
     * @throws SearchFlightRequestException
     */
    private void validateFlight(String airport) throws SearchFlightRequestException {
        String message = "Wrong airport ";
        if(airport == null) {
            throw new SearchFlightRequestException(message+"null");
        }
        if(!Pattern.matches("^[A-Z]+$",airport)) {
            throw new SearchFlightRequestException(message+airport);
        }
    }

    /**
     * Validate no negative days
     * @throws SearchFlightRequestException
     */
    private void validateDaysDeparture() throws SearchFlightRequestException {
        if (daysUntilDeparture < 0) {
            throw new SearchFlightRequestException("daysUntilDeparture must be >= 0");
        }
    }

    /**
     * Validate at least 1 passenger
     * @throws SearchFlightRequestException
     */
    private void validatePassengers() throws SearchFlightRequestException {
        if (passengerNumber <= 0) {
            throw new SearchFlightRequestException("daysUntilDeparture must be > 0");
        }
    }
}
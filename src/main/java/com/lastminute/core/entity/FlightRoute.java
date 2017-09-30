package com.lastminute.core.entity;

public class FlightRoute {

    private final String origin;
    private final String destination;
    private final String code;


    public FlightRoute(String origin, String destination, String code) {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
    }

    public String getCode() {
        return code;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }



}

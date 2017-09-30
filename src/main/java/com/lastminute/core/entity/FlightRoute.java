package com.lastminute.core.entity;

public class FlightRoute {

    private final String code;
    private final String origin;
    private final String destination;


    public FlightRoute(String code, String origin, String destination) {
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

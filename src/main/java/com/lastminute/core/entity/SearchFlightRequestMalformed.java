package com.lastminute.core.entity;

public class SearchFlightRequestMalformed extends Exception {

    SearchFlightRequestMalformed(String parameter) {
        super(parameter);
    }
}

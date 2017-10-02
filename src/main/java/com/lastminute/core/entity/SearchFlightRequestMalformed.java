package com.lastminute.core.entity;

/**
 * Thrown as validation error
 */
public class SearchFlightRequestMalformed extends Exception {

    SearchFlightRequestMalformed(String parameter) {
        super(parameter);
    }
}

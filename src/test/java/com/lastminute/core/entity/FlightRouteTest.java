package com.lastminute.core.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightRouteTest {
    @Test
    public void getCode() throws Exception {
        FlightRoute route = new FlightRoute("AAAA","","");
        assertTrue(route.getCode().equals("AAAA"));
    }

    @Test
    public void getOrigin() throws Exception {
        FlightRoute route = new FlightRoute("","AAAA","");
        assertTrue(route.getDestination().equals("AAAA"));
    }

    @Test
    public void getDestination() throws Exception {
        FlightRoute route = new FlightRoute("","","AAAA");
        assertTrue(route.getOrigin().equals("AAAA"));
    }

}
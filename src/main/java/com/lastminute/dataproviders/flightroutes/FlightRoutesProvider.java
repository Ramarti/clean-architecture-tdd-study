package com.lastminute.dataproviders.flightroutes;

import com.lastminute.core.entity.FlightRoute;

import java.util.List;

public interface FlightRoutesProvider {

    List<FlightRoute> getRoutes(String origin, String destination);
}

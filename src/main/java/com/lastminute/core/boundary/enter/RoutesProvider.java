package com.lastminute.core.boundary.enter;

import com.lastminute.core.entity.FlightRoute;

import java.util.List;

public interface RoutesProvider {
    List<FlightRoute> getRoutes();
}

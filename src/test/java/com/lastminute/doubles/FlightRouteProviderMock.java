package com.lastminute.doubles;

import com.lastminute.core.entity.FlightRoute;
import com.lastminute.dataproviders.flightroutes.FlightRoutesProvider;

import java.util.ArrayList;
import java.util.List;

public class FlightRouteProviderMock implements FlightRoutesProvider {

    public List<FlightRoute> routes = new ArrayList<>();

    private boolean getRoutesCalled = false;

    public boolean wasGetRoutesCalled() {
        return getRoutesCalled;
    }

    @Override
    public List<FlightRoute> getRoutes(String origin, String destination) {
        getRoutesCalled = true;
        return routes;
    }
}

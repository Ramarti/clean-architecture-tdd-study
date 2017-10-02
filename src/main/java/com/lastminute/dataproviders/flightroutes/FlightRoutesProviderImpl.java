package com.lastminute.dataproviders.flightroutes;

import com.lastminute.core.entity.FlightRoute;
import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Translates the csv rows to FlightRoutes
 */
public class FlightRoutesProviderImpl implements FlightRoutesProvider {


    public static FlightRoute fromRow(List<String> row) {
        if (row.size() != 3) {
            throw new IllegalArgumentException("Routes must have origin, destination and flight code");
        }
        return  new FlightRoute(row.get(0), row.get(1), row.get(2));
    }


    public static Boolean isSameFlightPath(FlightRoute route, String origin, String destination) {
        return  route.getDestination().equals(destination) && route.getOrigin().equals(origin);
    }


    @Override
    public List<FlightRoute> getRoutes(String origin, String destination, RecordReaderAdapter recordReader) {
        List<FlightRoute> routes = new ArrayList<>();
        List<List<String>> records = recordReader.readAllRecords();
        for (List<String> row : records) {
            FlightRoute route = FlightRoutesProviderImpl.fromRow(row);
            if (isSameFlightPath(route,origin,destination)) {
                routes.add(route);
            }
        }
        return routes;
    }
}

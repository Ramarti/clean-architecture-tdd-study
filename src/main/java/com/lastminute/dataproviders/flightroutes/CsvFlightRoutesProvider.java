package com.lastminute.dataproviders.flightroutes;

import com.lastminute.core.entity.FlightRoute;

import java.util.ArrayList;
import java.util.List;

import static com.lastminute.CsvFiles.readAllRecords;

public class CsvFlightRoutesProvider implements FlightRoutesProvider {

    private String fileName;

    public CsvFlightRoutesProvider(String fileName) {
        this.fileName = fileName;
    }


    public static FlightRoute fromRow(List<String> row) {
        if (row.size() != 3) {
            throw new IllegalArgumentException("Routes must have origin, destination and flight code");
        }
        return  new FlightRoute(row.get(0), row.get(1), row.get(2));
    }

    @Override
    public List<FlightRoute> getRoutes(String origin, String destination) {
        List<FlightRoute> routes = new ArrayList<>();
        List<List<String>> records = readAllRecords(fileName);
        for (List<String> row : records) {
            FlightRoute route = CsvFlightRoutesProvider.fromRow(row);
            if (isSameFlightPath(route,origin,destination)) {
                routes.add(route);
            }
        }
        return routes;
    }

    public static Boolean isSameFlightPath(FlightRoute route, String origin, String destination) {
        return  route.getDestination().equals(destination) && route.getOrigin().equals(origin);
    }
}

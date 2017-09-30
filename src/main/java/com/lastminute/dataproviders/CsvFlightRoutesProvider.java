package com.lastminute.dataproviders;

import com.lastminute.core.boundary.enter.RoutesProvider;
import com.lastminute.core.entity.FlightRoute;

import java.util.ArrayList;
import java.util.List;

import static com.lastminute.CsvFiles.readAllRecords;

public class CsvFlightRoutesProvider implements RoutesProvider {

    private String fileName;

    public CsvFlightRoutesProvider(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<FlightRoute> getRoutes() {
        List<FlightRoute> routes = new ArrayList<>();
        List<List<String>> records = readAllRecords(fileName);
        for (List<String> row : records) {
            routes.add(CsvFlightRoutesProvider.fromRow(row));
        }
        return routes;
    }

    public static FlightRoute fromRow(List<String> row) {
        if (row.size() != 3) {
            throw new IllegalArgumentException("Routes must have origin, destination and flight code");
        }
        return  new FlightRoute(row.get(0), row.get(1), row.get(2));
    }
}

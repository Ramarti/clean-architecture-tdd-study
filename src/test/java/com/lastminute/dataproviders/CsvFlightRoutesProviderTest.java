package com.lastminute.dataproviders;

import com.lastminute.core.entity.FlightRoute;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CsvFlightRoutesProviderTest {

    CsvFlightRoutesProvider provider;
    @Before
    public void setUp() throws Exception {
        provider = new CsvFlightRoutesProvider(fullPathTo("flight-routes.csv"));

    }

    @Test
    public void getRoutes() throws Exception {
        List<FlightRoute> routes = provider.getRoutes();
        assertFalse(routes.isEmpty());

    }

    @Test
    public void getRouteFromList() throws Exception {

        List<String> row = Arrays.asList("OR","DEST","CODE");
        FlightRoute route = CsvFlightRoutesProvider.fromRow(row);

        assertTrue(route.getOrigin().equals("OR"));
        assertTrue(route.getDestination().equals("DEST"));
        assertTrue(route.getCode().equals("CODE"));


    }

    @Test(expected = IllegalArgumentException.class)
    public void getRouteIncompleteList() throws Exception {
        List<String> row = Arrays.asList("OR","DEST");
        FlightRoute route = CsvFlightRoutesProvider.fromRow(row);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRouteTooLongList() throws Exception {
        List<String> row = Arrays.asList("OR","DEST","CODE","THING");
        FlightRoute route = CsvFlightRoutesProvider.fromRow(row);
    }

    private String fullPathTo(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
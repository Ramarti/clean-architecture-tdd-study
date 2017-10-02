package com.lastminute.dataproviders.flightroutes;

import com.lastminute.core.entity.FlightRoute;
import com.lastminute.doubles.MockCsvRecordReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FlightRoutesProviderImplTest {

    MockCsvRecordReader recordReader;
    FlightRoutesProviderImpl provider;
    @Before
    public void setUp() throws Exception {
        recordReader = new MockCsvRecordReader();
        provider = new FlightRoutesProviderImpl();
    }

    @Test
    public void getRoutesProviderCalled() throws Exception {
        //Given
        //When
        provider.getRoutes("LOL","NOPE",recordReader);
        //Then
        assertTrue(recordReader.wasReadRecordsCalled());
    }

    @Test
    public void getEmptyRoutes() throws Exception {
        //Given
        //When
        List<FlightRoute> routes = provider.getRoutes("LOL","NOPE",recordReader);
        //Then
        assertTrue(routes.isEmpty());
    }

    @Test
    public void getOneRoute() throws Exception {
        //Given
        recordReader.records.add(Arrays.asList("LEPE","ALBACETE","CODE"));
        //When
        List<FlightRoute> routes = provider.getRoutes("LEPE","ALBACETE",recordReader);
        //Then
        assertFalse(routes.isEmpty());
    }

    @Test
    public void getSeveralRoutes() throws Exception {
        //Given
        recordReader.records.add(Arrays.asList("LEPE","ALBACETE","CODE"));
        recordReader.records.add(Arrays.asList("LEPE","ALBACETE","CODE"));
        recordReader.records.add(Arrays.asList("LEPE","ALBACETE","CODE"));
        recordReader.records.add(Arrays.asList("LEPE","ALBACETE","CODE"));

        //When
        List<FlightRoute> routes = provider.getRoutes("LEPE","ALBACETE",recordReader);

        //Then
        assertEquals(4,routes.size());
    }



    @Test
    public void getRouteFromList() throws Exception {

        List<String> row = Arrays.asList("OR","DEST","CODE");
        FlightRoute route = FlightRoutesProviderImpl.fromRow(row);

        assertTrue(route.getOrigin().equals("OR"));
        assertTrue(route.getDestination().equals("DEST"));
        assertTrue(route.getCode().equals("CODE"));


    }

    @Test(expected = IllegalArgumentException.class)
    public void getRouteIncompleteList() throws Exception {
        List<String> row = Arrays.asList("OR","DEST");
        FlightRoute route = FlightRoutesProviderImpl.fromRow(row);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRouteTooLongList() throws Exception {
        List<String> row = Arrays.asList("OR","DEST","CODE","THING");
        FlightRoute route = FlightRoutesProviderImpl.fromRow(row);
    }


    private String fullPathTo(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
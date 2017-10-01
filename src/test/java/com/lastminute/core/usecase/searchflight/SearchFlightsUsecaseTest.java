package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.TestSetup;
import com.lastminute.core.entity.FlightRoute;
import com.lastminute.dataproviders.flightroutes.FlightRoutesProvider;
import com.lastminute.doubles.FlightPriceProviderMock;
import com.lastminute.doubles.FlightRouteProviderMock;
import com.lastminute.doubles.SearchFlightsOutputBoundarySpy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchFlightsUsecaseTest {


    SearchFlightsUsecase flightSearcher;
    FlightRouteProviderMock mockRoutesProvider;
    FlightPriceProviderMock mockPriceProvider;

    @Before
    public void setup() {
        TestSetup.setupContext();
        mockRoutesProvider = TestSetup.getMockRoutesProvider();
        mockPriceProvider = TestSetup.getMockPriceProvider();
        flightSearcher = new SearchFlightsUsecase(Context.getInstance());
    }


    @Test
    public void searchFlightsWiring() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","4"));
        SearchFlightRequest request = new SearchFlightRequest("MORDOR","LEGANES",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();
        //When
        flightSearcher.searchFlights(request,spy);
        //Then
        assertTrue(mockRoutesProvider.wasGetRoutesCalled());
        assertTrue(mockPriceProvider.wasPriceCalled);
        assertTrue(spy.called);
    }

    @Test
    public void searchFlightNoFlightsFound() {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("MORDOR","LEGANES",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();
        //When
        flightSearcher.searchFlights(request,spy);
        //Then
        assertTrue(spy.response.getResults().isEmpty());
    }

    @Test
    public void searchFlightPriceNotFound() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","U"));
        mockPriceProvider.priceFound = null;
        SearchFlightRequest request = new SearchFlightRequest("","",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();
        //When
        flightSearcher.searchFlights(request,spy);
        //Then
        assertTrue(spy.response.getResults().isEmpty());
    }

    @Test
    public void searchFlightPriceFound() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","U"));
        mockPriceProvider.priceFound = Double.valueOf(2);
        SearchFlightRequest request = new SearchFlightRequest("MORDOR","LEGANES",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();
        //When
        flightSearcher.searchFlights(request,spy);
        //Then
        assertFalse(spy.response.getResults().isEmpty());
    }

    @Test
    public void searchFlight2ResultsFound() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("ORI","DEST","A2"));
        mockRoutesProvider.routes.add(new FlightRoute("ORI","DEST","A3"));
        mockPriceProvider.priceFound = Double.valueOf(2);

        SearchFlightRequest request = new SearchFlightRequest("ORI","DEST",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();

        //When
        flightSearcher.searchFlights(request,spy);

        //Then
        assertEquals(2,spy.response.getResults().size());
    }



}
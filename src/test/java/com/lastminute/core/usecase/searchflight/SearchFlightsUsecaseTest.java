package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.TestSetup;
import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.entity.FlightRoute;
import com.lastminute.doubles.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchFlightsUsecaseTest {


    private SearchFlightsUsecase flightSearcher;
    private FlightRouteProviderMock mockRoutesProvider;
    private FlightPriceProviderMock mockPriceProvider;
    private MockDayPriceModifier mockDayPriceModifier;
    private MockPassengersPriceModifier mockPassengersPriceModifier;

    @Before
    public void setup() {
        TestSetup.setupContext();
        mockRoutesProvider = TestSetup.getMockRoutesProvider();
        mockPriceProvider = TestSetup.getMockPriceProvider();
        mockDayPriceModifier = new MockDayPriceModifier();

        mockPassengersPriceModifier = new MockPassengersPriceModifier();
        flightSearcher = new SearchFlightsUsecase(Context.getInstance(),mockDayPriceModifier,mockPassengersPriceModifier);
    }


    @Test
    public void searchFlightsWiring() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","4"));
        mockPriceProvider.priceFound = 3.0;
        mockDayPriceModifier.priceToReturn = new DayPriceModificationResult(2,2);
        SearchFlightRequest request = new SearchFlightRequest("MORDOR","LEGANES",1,2);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();

        //When
        flightSearcher.searchFlights(request,spy);

        //Then
        assertTrue(mockRoutesProvider.wasGetRoutesCalled());
        assertTrue(mockPriceProvider.wasPriceCalled);
        assertTrue(mockDayPriceModifier.wasModifierCalled());
        assertTrue(mockPassengersPriceModifier.wasModifierCalled());
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
        mockDayPriceModifier.priceToReturn = new DayPriceModificationResult(2,2);
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
        mockDayPriceModifier.priceToReturn = new DayPriceModificationResult(2,2);
        mockPriceProvider.priceFound = Double.valueOf(2);
        SearchFlightRequest request = new SearchFlightRequest("ORI","DEST",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();

        //When
        flightSearcher.searchFlights(request,spy);

        //Then
        assertEquals(2,spy.response.getResults().size());
    }





}
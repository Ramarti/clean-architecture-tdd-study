package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.TestSetup;
import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.entity.FlightRoute;
import com.lastminute.core.entity.SearchFlightDataProviders;
import com.lastminute.core.entity.SearchFlightPriceModifiers;
import com.lastminute.doubles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SearchFlightsUsecaseTest {


    private SearchFlightsUsecase flightSearcher;
    private FlightRouteProviderMock mockRoutesProvider;
    private FlightPriceProviderMock mockPriceProvider;
    private MockDayPriceModifier mockDayPriceModifier;
    private MockPassengersPriceModifier mockPassengersPriceModifier;
    private MockCsvRecordReader mockReader;

    @Before
    public void setup() {
        TestSetup.setupContext();
        mockRoutesProvider = TestSetup.getMockRoutesProvider();
        mockPriceProvider = TestSetup.getMockPriceProvider();
        mockDayPriceModifier = new MockDayPriceModifier();
        mockPassengersPriceModifier = new MockPassengersPriceModifier();
        SearchFlightPriceModifiers modifiers = new SearchFlightPriceModifiers(
                mockDayPriceModifier,
                mockPassengersPriceModifier
        );
        mockReader = new MockCsvRecordReader();
        SearchFlightDataProviders providers = new SearchFlightDataProviders(mockReader,mockReader);
        flightSearcher = new SearchFlightsUsecase(Context.getInstance(),modifiers,providers);
    }


    @Test
    public void searchFlightsWiring() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","4"));
        mockPriceProvider.priceFound = Optional.of(3.0);
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
    public void searchFlightsResponseFull() {
        //Given
        mockRoutesProvider.routes.add(new FlightRoute("MORDOR","LEGANES","4"));
        mockPriceProvider.priceFound = Optional.of(Double.valueOf(3.0));
        mockDayPriceModifier.priceToReturn = new DayPriceModificationResult(2,2);
        SearchFlightRequest request = new SearchFlightRequest("MORDOR","LEGANES",1,2);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();

        //When
        flightSearcher.searchFlights(request,spy);

        //Then
        assertNotNull(spy.response.getResults() );
        assertNotNull(spy.response.getRequest() );
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
        mockPriceProvider.priceFound = Optional.empty();
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
        mockPriceProvider.priceFound = Optional.of(Double.valueOf(2));
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
        mockPriceProvider.priceFound = Optional.of(Double.valueOf(2));
        SearchFlightRequest request = new SearchFlightRequest("ORI","DEST",1,1);
        SearchFlightsOutputBoundarySpy spy = new SearchFlightsOutputBoundarySpy();

        //When
        flightSearcher.searchFlights(request,spy);

        //Then
        assertEquals(2,spy.response.getResults().size());
    }





}
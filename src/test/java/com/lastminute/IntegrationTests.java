package com.lastminute;

import com.lastminute.core.entity.SearchFlightDataProviders;
import com.lastminute.core.entity.SearchFlightPriceModifiers;
import com.lastminute.core.managers.DayMultiplierFlightPriceModifier;
import com.lastminute.core.managers.MultiplePassengerFlightPriceModifier;
import com.lastminute.core.usecase.searchflight.*;
import com.lastminute.dataproviders.RecordReaderAdapter;
import com.lastminute.dataproviders.RecordReaderFactory;
import com.lastminute.dataproviders.flightprices.FlightPriceProviderImpl;
import com.lastminute.dataproviders.flightroutes.FlightRoutesProviderImpl;
import com.lastminute.entrypoints.SearchFlightPrintStreamView;
import com.lastminute.preparedOutputs.SearchViewOutputs;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    Context testContext;
    SearchFlightController controller;
    ByteArrayOutputStream outputStream;

    @Before
    public void setup() {
        setupContext();
        SearchFlightsPresenter presenter = new SearchFlightsPresenter();
        SearchFlightsUsecase usecase = getUsecase();
        SearchFlightPrintStreamView view = getSearchFlightConsoleView();
        controller = new SearchFlightController(presenter,usecase,view);

    }

    /*
    * 1 passenger, 31 days to the departure date, flying AMS -> FRA

  flights:

    * TK2372, 157.6 €
    * TK2659, 198.4 €
    * LH5909, 90.4 €
     */

    @Test
    public void test1Passenger31DaysAMStoFRA() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("AMS", "FRA", 31,1);
        //When
        controller.handleRequest(request);
        //Then
        String expected = SearchViewOutputs.get1Passenger31DaysAMStoFRA();
        assertEquals(expected,getResult());

    }

    /*

    * 3 passengers, 15 days to the departure date, flying LHR -> IST

  flights:

    * TK8891, 900 € (3 * (120% of 250))
    * LH1085, 532.8 € (3 * (120% of 148))
     */
    @Test
    public void test3Passenger15DaysLHRtoIST() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("LHR", "IST", 15,3);
        //LHR
        controller.handleRequest(request);
        //Then
        String expected = SearchViewOutputs.get3Passenger15daysLHRtoIST();
        assertEquals(expected,getResult());

    }

    /*
    * 2 passengers, 2 days to the departure date, flying BCN -> MAD

  flights:

    * IB2171, 777 € (2 * (150% of 259))
    * LH5496, 879 € (2 * (150% of 293))

     */
    @Test
    public void test2Passenger2DaysBCNtoMAD() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("BCN", "MAD", 2,2);
        //LHR
        controller.handleRequest(request);
        //Then
        String expected = SearchViewOutputs.get2Passenger2DaysBCNtoMAD();
        assertEquals(expected,getResult());

    }

    /*

    * CDG -> FRA

    no flights available
     */

    @Test
    public void test2Passenger2DaysCDGtoFRA() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("CDG", "FRA", 2,2);
        //LHR
        controller.handleRequest(request);
        //Then
        String expected = SearchViewOutputs.get2Passenger2DaysCDGtoFRA();
        assertEquals(expected,getResult());

    }



    private SearchFlightsUsecase getUsecase() {
        SearchFlightPriceModifiers modifiers = getSearchFlightPriceModifiers();
        SearchFlightDataProviders providers = getSearchFlightDataProviders();
        return new SearchFlightsUsecase(testContext, modifiers, providers);
    }

    private SearchFlightDataProviders getSearchFlightDataProviders() {
        RecordReaderAdapter flightsProvider = testContext.recordReaderFactory.csvRecordReader("flight-routes.csv");
        RecordReaderAdapter pricesProvider = testContext.recordReaderFactory.csvRecordReader("flight-prices.csv");
        return new SearchFlightDataProviders(pricesProvider,flightsProvider);
    }

    private SearchFlightPriceModifiers getSearchFlightPriceModifiers() {
        DayPriceModifier dayPriceModifier = new DayMultiplierFlightPriceModifier();
        PassengerPriceModifier passengerPriceModifier = new MultiplePassengerFlightPriceModifier();
        return new SearchFlightPriceModifiers(dayPriceModifier,passengerPriceModifier);
    }

    private SearchFlightPrintStreamView getSearchFlightConsoleView() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        return new SearchFlightPrintStreamView(ps);
    }

    private void setupContext() {
        testContext = Context.getInstance();
        testContext.recordReaderFactory = new RecordReaderFactory();
        testContext.flightRoutesProvider = new FlightRoutesProviderImpl();
        testContext.flightPriceProvider = new FlightPriceProviderImpl();

    }

    private String getResult() {
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

}

package com.lastminute.dataproviders.flightprices;

import com.lastminute.doubles.MockCsvRecordReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FlightPriceProviderImplTest {

    FlightPriceProviderImpl provider;
    MockCsvRecordReader recordReader;

    @Before
    public void setUp() throws Exception {
        provider = new FlightPriceProviderImpl();
        recordReader = new MockCsvRecordReader();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void readerCalled() throws Exception {
        //Given
        //When
        Optional<Double> price = provider.getPriceForFlight("NOPE",recordReader);
        //Then
        assertTrue(recordReader.wasReadRecordsCalled());
    }

    @Test
    public void getFlightPrice() throws Exception {
        //Given
        recordReader.records.add(recordFor("LOLOL","197"));
        //When
        Optional<Double> price = provider.getPriceForFlight("LOLOL",recordReader);
        //Then
        assertTrue(price.isPresent());
        assertEquals(Double.valueOf(197),price.get());
    }

    @Test
    public void  getNonExistentFlightPrice() {
        //Given
        recordReader.records.add(recordFor("LOLOL","197"));
        //When
        Optional<Double> price = provider.getPriceForFlight("NOPE",recordReader);
        //Then
        assertFalse(price.isPresent());
    }

    @Test
    public void  getNonExistentFlightPriceNoFlights() {
        //Given
        //When
        Optional<Double> price = provider.getPriceForFlight("NOPE",recordReader);
        //Then
        assertFalse(price.isPresent());
    }

    private static List<String> recordFor(String code, String price) {
        List<String> result = new ArrayList<>();
        result.add(code);
        result.add(price);
        return result;
    }
}
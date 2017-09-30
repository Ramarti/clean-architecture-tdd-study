package com.lastminute.dataproviders.flightprices;

import com.lastminute.dataproviders.flightroutes.CsvFlightRoutesProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CsvFlightPriceProviderTest {

    CsvFlightPriceProvider provider;

    @Before
    public void setUp() throws Exception {
        provider = new CsvFlightPriceProvider(fullPathTo("flight-prices.csv"));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getFlightPrice() throws Exception {
        Double price = provider.getPriceForFlight("TK2372");
        assertTrue(price.equals(new Double(197)));
    }

    @Test
    public void  getNonExistentFlightPrice() {
        Double price = provider.getPriceForFlight("LOLWHAT");
        assertNull(price);
    }

    private String fullPathTo(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }

}
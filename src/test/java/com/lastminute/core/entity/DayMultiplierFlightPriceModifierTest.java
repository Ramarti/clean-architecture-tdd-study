package com.lastminute.core.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayMultiplierFlightPriceModifierTest {

    DayMultiplierFlightPriceModifier modifier;

    @Before
    public void setUp() throws Exception {
        modifier = new DayMultiplierFlightPriceModifier();

    }
    /*
    | days prior to the departure date | % of the base price |
    |----------------------------------|---------------------|
    | more than 30 (i.e. >= 31)        | 80%                 |
    | 30 - 16                          | 100%                |
    | 15 - 3                           | 120%                |
    | less that 3 (i.e. <= 2)          | 150%                |
     */

    @Test
    public void modifyForLessThan3Days() {
        //Given
        PriceParameters parameters = new PriceParameters(2);
        //When
        Double result = modifier.modify(1.0,parameters);
        //Then
        assertEquals(result,new Double(1.5));
    }

    @Test
    public void modifyForLessThan15Days() {
        //Given
        PriceParameters parameters = new PriceParameters(10);
        //When
        Double result = modifier.modify(1.0,parameters);
        //Then
        assertEquals(result,new Double(1.2));
    }
    @Test
    public void modifyForLessthan30Days() {
        //Given
        PriceParameters parameters = new PriceParameters(25);
        //When
        Double result = modifier.modify(1.0,parameters);
        //Then
        assertEquals(result,new Double(1));
    }

    @Test
    public  void modifyForMoreThan30Days() {
        //Given
        PriceParameters parameters = new PriceParameters(35);
        //When
        Double result = modifier.modify(1.0,parameters);
        //Then
        assertEquals(result,new Double(0.8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyForNegativeDays() {
        //Given
        PriceParameters parameters = new PriceParameters(-2);

    }

}
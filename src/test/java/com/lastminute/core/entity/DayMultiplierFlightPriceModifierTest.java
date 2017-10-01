package com.lastminute.core.entity;

import com.lastminute.core.managers.DayMultiplierFlightPriceModifier;
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
    public void modifyFor3Days() {
        //Given
        int days = 3;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.2));
    }

    @Test
    public void modifyFor15Days() {
        //Given
        int days = 15;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.2));
    }

    @Test
    public void modifyFor16Days() {
        //Given
        int days = 16;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.0));
    }

    @Test
    public void modifyFor30Days() {
        //Given
        int days = 30;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.0));
    }

    @Test
    public void modifyForLessThan3Days() {
        //Given
        int days = 2;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.5));
    }

    @Test
    public void modifyForLessThan15Days() {
        //Given
        int days = 10;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.2));
    }
    @Test
    public void modifyForLessthan30Days() {
        //Given
        int days = 25;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(1.0));
    }

    @Test
    public  void modifyForMoreThan30Days() {
        //Given
        int days = 31;
        //When
        Double result = modifier.modifyPrice(1.0,days);
        //Then
        assertEquals(result,Double.valueOf(0.8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyForNegativeDays() {
        //Given
        int days = -1;
        //When
        Double result = modifier.modifyPrice(1.0,days);

    }

}
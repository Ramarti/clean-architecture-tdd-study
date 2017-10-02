package com.lastminute.core.managers;

import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.managers.DayMultiplierFlightPriceModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Plenty of test here, because money.
 */
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
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.4));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.2));

    }

    @Test
    public void modifyFor15Days() {
        //Given
        int days = 15;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.4));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.2));

    }

    @Test
    public void modifyFor16Days() {
        //Given
        int days = 16;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.0));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.0));
    }

    @Test
    public void modifyFor30Days() {
        //Given
        int days = 30;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.0));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.0));
    }

    @Test
    public void modifyForLessThan3Days() {
        //Given
        int days = 2;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(3));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.5));
    }

    @Test
    public void modifyForLessThan15Days() {
        //Given
        int days = 10;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.4));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.2));
    }
    @Test
    public void modifyForLessthan30Days() {
        //Given
        int days = 25;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(2.0));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(1.0));
    }

    @Test
    public  void modifyForMoreThan30Days() {
        //Given
        int days = 31;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);
        //Then
        assertEquals(Double.valueOf(result.getResult()),Double.valueOf(1.6));
        assertEquals(Double.valueOf(result.getModifier()),Double.valueOf(0.8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyForNegativeDays() {
        //Given
        int days = -1;
        //When
        DayPriceModificationResult result = modifier.modifyPrice(2.0,days);

    }

}
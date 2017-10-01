package com.lastminute.core.managers;

import com.lastminute.core.usecase.searchflight.PassengerPriceModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiplePassengerFlightPriceModifierTest {

    MultiplePassengerFlightPriceModifier modifier;

    @Before
    public void setup() {
        modifier = new MultiplePassengerFlightPriceModifier();
    }

    @Test
    public void modifyPrice() throws Exception {
        //Given
        double rawPrice = 1.0;
        int passenger = 3;

        //Then
        double result = modifier.modifyPrice(rawPrice,passenger);

        //When
        assertEquals(Double.valueOf(3.0),Double.valueOf(result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyPriceIllegalPassengerNumber() throws Exception {
        //Given
        double rawPrice = 1.0;
        int passenger = -1;

        //Then
        double result = modifier.modifyPrice(rawPrice,passenger);

    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyPriceNoPassengerNumber() throws Exception {
        //Given
        double rawPrice = 1.0;
        int passenger = 0;

        //Then
        double result = modifier.modifyPrice(rawPrice,passenger);

    }

}
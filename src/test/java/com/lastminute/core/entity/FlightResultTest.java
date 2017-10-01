package com.lastminute.core.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightResultTest {

    @Test
    public void testFullyBuiltFully() {
        //Given
        FlightResult.Builder builder = new FlightResult
                .Builder("HEY",2)
                .currency("#")
                .dayModifierApplied(1,1)
                .passengerModifierApplied(2);
        //When
        FlightResult result = builder.build();
        //Then
        assertEquals(result.getCurrency(),"#");
        assertEquals(result.getCode(),"HEY");
        assertEquals(Double.valueOf(result.getOriginalPrice()),Double.valueOf(2));
        assertEquals(Double.valueOf(result.getDayModifierApplied()),Double.valueOf(1));
        assertEquals(Double.valueOf(result.getFinalPrice()),Double.valueOf(2));

    }

    @Test
    public void testFullyBuiltFullyDifferentModifierOrder() {
        //Given
        FlightResult.Builder builder = new FlightResult
                .Builder("HEY",2)
                .currency("#")
                .passengerModifierApplied(2)
                .dayModifierApplied(1,1);
        //When
        FlightResult result = builder.build();
        //Then
        assertEquals(result.getCurrency(),"#");
        assertEquals(result.getCode(),"HEY");
        assertEquals(Double.valueOf(result.getOriginalPrice()),Double.valueOf(2));
        assertEquals(Double.valueOf(result.getDayModifierApplied()),Double.valueOf(1));
        assertEquals(Double.valueOf(result.getFinalPrice()),Double.valueOf(1));

    }

    @Test(expected = IllegalStateException.class)
    public void testFullyNotFullyBuilt() {
        //Given
        FlightResult.Builder builder = new FlightResult
                .Builder("HEY",2)
                .passengerModifierApplied(2);
        //When
        FlightResult result = builder.build();

    }



}
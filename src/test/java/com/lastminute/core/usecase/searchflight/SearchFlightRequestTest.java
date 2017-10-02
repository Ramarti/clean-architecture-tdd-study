package com.lastminute.core.usecase.searchflight;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchFlightRequestTest {


    @Test
    public void validateOK() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("ORI","DEST",2,3);
        //When
        request.validate();
        //Then
        assertEquals(request.getDestination(),"DEST");
        assertEquals(request.getOrigin(),"ORI");
        assertEquals(request.getDaysUntilDeparture(),2);
        assertEquals(request.getPassengerNumber(),3);

    }

    @Test(expected = SearchFlightRequestException.class)
    public void validateWrongOrigin() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("123%%","DEST",2,3);
        //When
        request.validate();

    }

    @Test(expected = SearchFlightRequestException.class)
    public void validateWrongDestination() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("SS","123%",2,3);
        //When
        request.validate();

    }

    @Test(expected = SearchFlightRequestException.class)
    public void validateWrongDepartureDays() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("SS","SS",-1,3);
        //When
        request.validate();

    }

    @Test(expected = SearchFlightRequestException.class)
    public void validateWrongPassengerNumber() throws Exception {
        //Given
        SearchFlightRequest request = new SearchFlightRequest("SS","123%%\"",2,0);
        //When
        request.validate();

    }



}
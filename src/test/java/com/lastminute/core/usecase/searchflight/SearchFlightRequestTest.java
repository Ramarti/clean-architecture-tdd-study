package com.lastminute.core.usecase.searchflight;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchFlightRequestTest {

    @Test
    public void allDataOK() {
        //Given
        //When
        SearchFlightRequest request = new SearchFlightRequest("ORI","DEST",2,3);
        //Then
        assertEquals(request.getDestination(),"DEST");
        assertEquals(request.getOrigin(),"ORI");
        assertEquals(request.getDaysUntilDeparture(),2);
        assertEquals(request.getPassengerNumber(),3);
    }

    
}
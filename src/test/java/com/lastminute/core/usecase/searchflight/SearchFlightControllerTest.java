package com.lastminute.core.usecase.searchflight;

import com.lastminute.doubles.MockSearchFlightPresenter;
import com.lastminute.doubles.MockSearchFlightsUsecase;
import com.lastminute.doubles.SearchFlightViewSpy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchFlightControllerTest {

    SearchFlightController controller;
    private MockSearchFlightPresenter mockPresenter;
    private MockSearchFlightsUsecase mockUsecase;
    private SearchFlightViewSpy viewSpy;

    @Before
    public void setup() {
        mockPresenter = new MockSearchFlightPresenter();
        mockUsecase = new MockSearchFlightsUsecase();
        viewSpy = new SearchFlightViewSpy();
        controller = new SearchFlightController(mockPresenter,mockUsecase,viewSpy);

    }

    @Test
    public void useCaseCalled() throws Exception {
        //When
        //Given
        controller.handleRequest(getDummyFlightRequest());
        //Then
        assertTrue(mockUsecase.isSearchFlightsCalled());

    }

    @Test
    public void presenterViewModelCalled() throws Exception {
        //When
        //Given
        controller.handleRequest(getDummyFlightRequest());
        //Then
        assertTrue(mockPresenter.isGetViewModelCalled());
    }

    @Test
    public void viewMadeViewable() throws Exception {
        //When
        //Given
        controller.handleRequest(getDummyFlightRequest());
        //Then
        assertTrue(viewSpy.wasMadeViewable());
    }


    private static SearchFlightRequest getDummyFlightRequest() {
        return new SearchFlightRequest("EE","EE",1,1);
    }


}
package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.core.entity.FlightResult;
import com.lastminute.core.entity.FlightRoute;
import com.lastminute.core.managers.DayPriceModifier;

import java.util.ArrayList;
import java.util.List;

public class SearchFlightsUsecase implements  SearchFlightsInputBoundary {


    private Context context;
    private DayPriceModifier dayPriceModifier;

    public SearchFlightsUsecase(Context context,DayPriceModifier dayPriceModifier) {
        this.context = context;
        this.dayPriceModifier = dayPriceModifier;
    }

    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {
        List<FlightRoute> routes = context.flightRoutesProvider.getRoutes(request.getOrigin(),request.getDestination());
        List<FlightResult> results = new ArrayList< FlightResult>();
        for ( FlightRoute route : routes) {
            Double price = context.flightPriceProvider.getPriceForFlight(route.getCode());
            if (price != null) {
                price = dayPriceModifier.modifyPrice(price,request.getDaysUntilDeparture());
                results.add(new FlightResult(route.getCode(), price, context.defaultCurrency));
            } else {
                //TODO notify missing price...is this an error? should trigger another search? just don't show the flight?
            }
        }
        presenter.present(new SearchFlightResponse(results));
    }



}

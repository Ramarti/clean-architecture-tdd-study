package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.core.entity.FlightResult;
import com.lastminute.core.entity.FlightRoute;

import java.util.ArrayList;
import java.util.List;

public class SearchFlightsUsecase implements  SearchFlightsInputBoundary {


    private Context context;

    public SearchFlightsUsecase(Context context) {
        this.context = context;
    }

    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {
        List<FlightRoute> routes = context.flightRoutesProvider.getRoutes(request.getOrigin(),request.getDestination());
        List<FlightResult> results = new ArrayList< FlightResult>();
        for ( FlightRoute route : routes) {
            Double price = context.flightPriceProvider.getPriceForFlight(route.getCode());
            if (price != null) {
                results.add(new FlightResult(route.getCode(), price, context.defaultCurrency));
            }
        }
        presenter.present(new SearchFlightResponse(results));
    }



}

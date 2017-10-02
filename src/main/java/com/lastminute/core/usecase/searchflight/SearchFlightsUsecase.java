package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.entity.FlightResult;
import com.lastminute.core.entity.FlightRoute;

import java.util.ArrayList;
import java.util.List;

public class SearchFlightsUsecase implements  SearchFlightsInputBoundary {


    private Context context;
    private DayPriceModifier dayPriceModifier;
    private PassengerPriceModifier passengerPriceModifier;

    public SearchFlightsUsecase(Context context, DayPriceModifier dayPriceModifier, PassengerPriceModifier passengerPriceModifier) {
        this.context = context;
        this.dayPriceModifier = dayPriceModifier;
        this.passengerPriceModifier = passengerPriceModifier;
    }

    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {
        List<FlightRoute> routes = context.flightRoutesProvider.getRoutes(request.getOrigin(),request.getDestination());
        List<FlightResult> results = new ArrayList< FlightResult>();
        for ( FlightRoute route : routes) {
            Double price = context.flightPriceProvider.getPriceForFlight(route.getCode());
            if (price != null) {
                FlightResult.Builder resultBuilder = new FlightResult.Builder(route.getCode(),price);
                resultBuilder = modifyDays(resultBuilder,request);
                resultBuilder = modifyPriceByPassengers(resultBuilder,request);
                resultBuilder = resultBuilder.currency(context.defaultCurrency);
                results.add(resultBuilder.build());

            } else {
                //TODO notify missing price...is this an error? should trigger another search? just don't show the flight?
            }
        }
        presenter.present(new SearchFlightResponse(results,request));
    }

    private FlightResult.Builder modifyPriceByPassengers(FlightResult.Builder builder,SearchFlightRequest request) {
        double afterPassengerPrice = passengerPriceModifier.modifyPrice(builder.getCurrentPrice().get(),request.getPassengerNumber());
        return  builder.passengerModifierApplied(afterPassengerPrice);
    }

    private FlightResult.Builder modifyDays(FlightResult.Builder builder, SearchFlightRequest request) {
        DayPriceModificationResult dayPriceResult = dayPriceModifier
                .modifyPrice(
                        builder.getOriginalPrice().get(),
                        request.getDaysUntilDeparture()
                );
        return builder.dayModifierApplied(dayPriceResult.getModifier(),dayPriceResult.getResult());
    }




}

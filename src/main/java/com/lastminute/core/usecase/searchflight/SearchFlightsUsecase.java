package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.core.entity.*;
import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchFlightsUsecase implements  SearchFlightsInputBoundary {


    private Context context;
    private DayPriceModifier dayPriceModifier;
    private PassengerPriceModifier passengerPriceModifier;
    private RecordReaderAdapter routesAdapter;
    private RecordReaderAdapter pricesAdapter;

    public SearchFlightsUsecase(Context context,
                                SearchFlightPriceModifiers modifiers,
                                SearchFlightDataProviders providers
                                ) {
        this.context = context;
        this.dayPriceModifier = modifiers.getDayPriceModifier();
        this.passengerPriceModifier = modifiers.getPassengerPriceModifier();
        this.routesAdapter = providers.getRoutesReader();
        this.pricesAdapter = providers.getPricesReader();
    }

    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {
        //Getting routes for trip
        List<FlightRoute> routes = context.flightRoutesProvider.getRoutes(request.getOrigin(),request.getDestination(),routesAdapter);

        List<FlightResult> results = new ArrayList< FlightResult>();
        for ( FlightRoute route : routes) {
            Optional<Double> price = context.flightPriceProvider.getPriceForFlight(route.getCode(),pricesAdapter);
            if (price.isPresent()) {
                FlightResult.Builder resultBuilder = new FlightResult.Builder(route.getCode(),price.get());
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

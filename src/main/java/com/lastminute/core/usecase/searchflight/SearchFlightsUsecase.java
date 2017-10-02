package com.lastminute.core.usecase.searchflight;

import com.lastminute.Context;
import com.lastminute.core.entity.*;
import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The interactor of the exercise
 */
public class SearchFlightsUsecase implements  SearchFlightsInputBoundary {


    private Context context;
    private DayPriceModifier dayPriceModifier;
    private PassengerPriceModifier passengerPriceModifier;
    private RecordReaderAdapter routesAdapter;
    private RecordReaderAdapter pricesAdapter;

    /**
     * Construct interactor for the useCase
     * @param context object holding gateway dependecies
     * @param modifiers business rules
     * @param providers
     */
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

    /**
     * Search for the flight requested, calculate the prices and trigger the presentation
     *
     * @param request
     * @param presenter
     */
    @Override
    public void searchFlights(SearchFlightRequest request, SearchFlightsOutputBoundary presenter) {

        // I'm not that familiar with the Java 8 stream and FunctionalInterfaces quirks,
        // so I did this "the old fashioned way" with a for so I don't get tangled in implementation details
        //
        // Anyway i know stream is more elegant and helps with parallelism so this shouldn't be hard to refactor
        // map the flightRoute to a builder and applying modifiers to it until we get the Result list
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
                //TODO what to do with missing flight price
                // We could log it internally if this is considered an error but don't escalate to the user.
                // If it makes sense (maybe flight list is obsolete?) this should notify the presenter to trigger a retry
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

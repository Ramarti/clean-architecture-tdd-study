package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.FlightResult;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Constructs the (in this case) string representation needed by the defined view (a prompt)
 *
 */
public class SearchFlightsPresenter implements SearchFlightsOutputBoundary {

    SearchFlightViewModel viewModel;

    @Override
    public SearchFlightViewModel getViewModel() {
        return viewModel;
    }

    /**
     * Constructs the (in this case) string representation needed by the defined view (a prompt) with use case output
     * @param response Use case output
     */
    @Override
    public void present(SearchFlightResponse response) {
        String requestSummary;
        if (response.getResults().isEmpty()) {
            requestSummary = getResponseSummaryWithNoResults(response);
        } else {
            requestSummary = getResponseSummaryWithResults(response);
        }

        viewModel = new SearchFlightViewModel(requestSummary);
    }

    //****** No results

    private String getResponseSummaryWithNoResults(SearchFlightResponse response) {
        SearchFlightRequest request = response.getRequest();
        return String.join("\n",
                getDestinationPhraseNoResults(request.getOrigin(),request.getDestination()),
                "",
                "  no flights available"
                );
    }
    private String getDestinationPhraseNoResults(String origin, String destination) {
        return String.join(" ",
                "*",
                origin,
                "->",
                destination
        );

    }



    //****** Results

    private String getResponseSummaryWithResults(SearchFlightResponse response) {
        String result = String.join("\n",
                getRequestSummaryWithResults(response.getRequest()),
                "",
                getFlightsSummary(response.getResults(),response.getRequest().getPassengerNumber())
        );

        return result.replaceAll("\\n*$","");
    }

    private String getRequestSummaryWithResults(SearchFlightRequest request) {
        return String.join(" ",
                "*",
                String.valueOf(request.getPassengerNumber()),
                getPassengerPhrase(request.getPassengerNumber()),
                getDatePhrase(request.getDaysUntilDeparture()),
                getDestinationPhraseResults(request.getOrigin(),request.getDestination())
        );
    }
    private String getDestinationPhraseResults(String origin, String destination) {
        return String.join(" ",
                "flying",
                origin,
                "->",
                destination
        );
    }

    private String getPassengerPhrase(int number) {
        if (number == 1) {
            return "passenger,";
        } else {
            return "passengers,";
        }
    }

    private String getDatePhrase(int days) {
        if (days == 0) {
            return "";
        } else if (days == 1) {
            return "1 days to the departure date,";
        } else {
            return String.valueOf(days)+" days to the departure date,";

        }
    }



    private String getFlightsSummary(List<FlightResult> results,int passengers) {
        if (results.isEmpty()) {
            return "  no flights available";
        }

        StringBuilder builder = new StringBuilder("  flights:");
        builder.append("\n");
        StringBuilder flightsBuilder = new StringBuilder();
        for (FlightResult result : results) {
            addFlightLine(passengers, flightsBuilder, result);
            flightsBuilder.append("\n");
        }
        flightsBuilder.deleteCharAt(flightsBuilder.length()-1);
        builder.append(flightsBuilder.toString());
       return builder.toString();
    }

    private void addFlightLine(int passengers, StringBuilder flightsBuilder, FlightResult result) {
        flightsBuilder.append(String.join(" ",
                "    *",
                result.getCode()+",",
                formatPriceValue(result.getFinalPrice()),
                result.getCurrency()
                ));
        if (hasDayModifier(result) && passengers > 1) {
            flightsBuilder.append(String.join(" ",
                    "",
                    getPassengerModifierSummary(passengers),
                    getDayModifier(result.getDayModifierApplied()),
                    getOfOriginalPrice(result.getOriginalPrice())+")"

                    ));
        } else if (hasDayModifier(result) && passengers == 1) {
            flightsBuilder.append(String.join(" ",
                    "",
                    getDayModifier(result.getDayModifierApplied()),
                    getOfOriginalPrice(result.getOriginalPrice())
            ));
        }
    }


    private String formatPriceValue(double value) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.#", otherSymbols);
        return df.format(value);
    }

    private String getDayModifier(double value) {
        return "("+(new DecimalFormat("##0").format(value*100))+"%";
    }

    private String getPassengerModifierSummary(int passengers) {
        if (passengers > 1) {
            return "("+String.valueOf(passengers)+" *";
        } else {
            return "";
        }
    }



    private String getOfOriginalPrice(double original) {
        return "of "+formatPriceValue(original)+")";
    }

    private boolean hasDayModifier(FlightResult result) {
        return Double.valueOf(result.getDayModifierApplied()) > 1.0;
    }


}

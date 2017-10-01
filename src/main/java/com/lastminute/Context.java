package com.lastminute;

import com.lastminute.dataproviders.flightprices.FlightPriceProvider;
import com.lastminute.dataproviders.flightroutes.FlightRoutesProvider;

public class Context {
    public static FlightPriceProvider flightPriceProvider;
    public static FlightRoutesProvider flightRoutesProvider;
    public static String defaultCurrency = "€";
}

package com.lastminute;

import com.lastminute.dataproviders.RecordReaderFactory;
import com.lastminute.dataproviders.flightprices.FlightPriceProvider;
import com.lastminute.dataproviders.flightroutes.FlightRoutesProvider;

/**
 * Thread safe singleton that helps with some of the dependency injection
 */
public class Context {

    public FlightPriceProvider flightPriceProvider;
    public FlightRoutesProvider flightRoutesProvider;
    public RecordReaderFactory recordReaderFactory;
    //TODO put this with the currency logic when implemented
    public String defaultCurrency = "€";

    private Context(){ }

    private static class Holder {
        private static final Context instance = new Context();
    }

    public static Context getInstance() {
        return Holder.instance;
    }
}
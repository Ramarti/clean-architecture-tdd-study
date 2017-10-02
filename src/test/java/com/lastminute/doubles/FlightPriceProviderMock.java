package com.lastminute.doubles;

import com.lastminute.dataproviders.RecordReaderAdapter;
import com.lastminute.dataproviders.flightprices.FlightPriceProvider;

import java.util.Optional;

public class FlightPriceProviderMock implements FlightPriceProvider {

    public boolean wasPriceCalled = false;
    public Optional<Double> priceFound;

    @Override
    public Optional<Double> getPriceForFlight(String code, RecordReaderAdapter recordReader) {
        wasPriceCalled = true;
        return priceFound;
    }
}

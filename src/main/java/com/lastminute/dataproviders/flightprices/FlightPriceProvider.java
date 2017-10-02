package com.lastminute.dataproviders.flightprices;

import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.Optional;

public interface FlightPriceProvider {


    Optional<Double> getPriceForFlight(String code, RecordReaderAdapter recordReader);

}

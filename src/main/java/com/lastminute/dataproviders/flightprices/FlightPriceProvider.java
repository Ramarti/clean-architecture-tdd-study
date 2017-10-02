package com.lastminute.dataproviders.flightprices;

import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.Optional;

/**
 * Gateway to price providers
 */
public interface FlightPriceProvider {

    /**
     * Search for the price of a flight
     * If we had rules for the records, we should validate code so we don't send
     * anything potentially dangerous to the actual provider
     * @param code
     * @param recordReader
     * @return
     */
    Optional<Double> getPriceForFlight(String code, RecordReaderAdapter recordReader);

}

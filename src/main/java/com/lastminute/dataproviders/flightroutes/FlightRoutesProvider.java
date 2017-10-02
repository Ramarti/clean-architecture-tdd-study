package com.lastminute.dataproviders.flightroutes;

import com.lastminute.core.entity.FlightRoute;
import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.List;

/**
 * Gateway to get flight Routes
 */
public interface FlightRoutesProvider {

    /**
     * Search for the desired route between airports
     * If we had rules for the records, we should validate origin and destination so we don't send
     * anything potentially dangerous to the actual provider
     *
     * @param origin
     * @param destination
     * @param recordReader
     * @return
     */
    List<FlightRoute> getRoutes(String origin, String destination,RecordReaderAdapter recordReader);
}

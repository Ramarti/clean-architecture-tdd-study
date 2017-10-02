package com.lastminute.core.entity;

import com.lastminute.dataproviders.RecordReaderAdapter;

/**
 * Grouping for useCase constructor
 */
public class SearchFlightDataProviders {
    private RecordReaderAdapter pricesReader;
    private RecordReaderAdapter routesReder;

    public SearchFlightDataProviders(RecordReaderAdapter pricesReader, RecordReaderAdapter routesReder) {
        this.pricesReader = pricesReader;
        this.routesReder = routesReder;
    }

    public RecordReaderAdapter getRoutesReader() {
        return routesReder;
    }

    public RecordReaderAdapter getPricesReader() {
        return pricesReader;
    }
}

package com.lastminute.dataproviders.flightprices;

import com.lastminute.core.entity.FlightRoute;
import com.lastminute.dataproviders.flightroutes.CsvFlightRoutesProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lastminute.CsvFiles.readAllRecords;

public class CsvFlightPriceProvider implements FlightPriceProvider {

    private String fileName;

    private Map<String,Double> prices;

    public CsvFlightPriceProvider(String fileName) {
        this.fileName = fileName;
        prices = new HashMap<>();
    }

    private void loadPrices() {
        prices = new HashMap<>();
        List<List<String>> records = readAllRecords(fileName);
        for (List<String> row : records) {
            prices.put(row.get(0),Double.parseDouble(row.get(1)));
        }
    }

    @Override
    public Double getPriceForFlight(String code) {
        if (prices.isEmpty()) {
            loadPrices();
        }
        return prices.get(code);
    }
}

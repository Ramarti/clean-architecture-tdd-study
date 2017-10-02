package com.lastminute.dataproviders.flightprices;

import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.lastminute.utils.CsvFiles.readAllRecords;

public class FlightPriceProviderImpl implements FlightPriceProvider {


    private Map<String,Double> prices;

    public FlightPriceProviderImpl() {
        prices = new HashMap<>();
    }

    private void loadPrices(RecordReaderAdapter recordReader) {
        prices = new HashMap<>();
        List<List<String>> records = recordReader.readAllRecords();
        for (List<String> row : records) {
            prices.put(row.get(0),Double.parseDouble(row.get(1)));
        }
    }

    @Override
    public Optional<Double> getPriceForFlight(String code, RecordReaderAdapter recordReader) {
        if (prices.isEmpty()) {
            loadPrices(recordReader);
        }
        return Optional.ofNullable(prices.get(code));
    }



}

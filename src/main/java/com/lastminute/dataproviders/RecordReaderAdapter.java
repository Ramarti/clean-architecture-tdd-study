package com.lastminute.dataproviders;

import java.util.List;

/**
 * Interface allow us to inject and mock the CsvReader
 *
 */
public interface RecordReaderAdapter {

    List<List<String>> readAllRecords();

}
package com.lastminute.dataproviders;

/**
 * Factory of CsvRecordReaderAdapter so the Context can produce several for different data files
 */
public class RecordReaderFactory {

    public CsvRecordReaderAdapter csvRecordReader(String fileName) {
        return new CsvRecordReaderAdapter(fileName);
    }
}

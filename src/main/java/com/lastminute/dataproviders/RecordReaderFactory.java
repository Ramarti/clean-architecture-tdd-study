package com.lastminute.dataproviders;

public class RecordReaderFactory {

    public CsvRecordReaderAdapter csvRecordReader(String fileName) {
        return new CsvRecordReaderAdapter(fileName);
    }
}

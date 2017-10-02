package com.lastminute.dataproviders;

public class RecordReaderFactory {

    public static CsvRecordReaderAdapter csvRecordReader(String fileName) {
        return new CsvRecordReaderAdapter(fileName);
    }
}

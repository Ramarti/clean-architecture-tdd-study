package com.lastminute.doubles;

import com.lastminute.dataproviders.RecordReaderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MockCsvRecordReader implements RecordReaderAdapter {

    public List<List<String>> records;
    private boolean readRecordsCalled = false;

    public MockCsvRecordReader() {
        records = new ArrayList<>();
    }

    @Override
    public List<List<String>> readAllRecords() {
        readRecordsCalled = true;
        return records;
    }

    public boolean wasReadRecordsCalled() {
        return readRecordsCalled;
    }
}

package com.lastminute.dataproviders;

import com.lastminute.utils.CsvFiles;

import java.util.List;

/**
 * Wrapper of the actual CsvReader
 */
public class CsvRecordReaderAdapter implements RecordReaderAdapter {

    String path;

    CsvRecordReaderAdapter(String fileName) {
        this.path = fullPathTo(fileName);

    }

    @Override
    public List<List<String>> readAllRecords() {
        return CsvFiles.readAllRecords(path);
    }

    private String fullPathTo(String fileName)
    {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }

}

package lesson.interpreter2;

import java.util.ArrayList;
import java.util.List;

public class CsvData {

    private List<String> header;
    private List<List<String>> records;

    public CsvData() {
        header = new ArrayList<String>();
        records = new ArrayList<List<String>>();
    }

    public List<String> getHeader() {
        return header;
    }

    public List<List<String>> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "CsvData [header=" + header + ", records=" + records + "]";
    }

}

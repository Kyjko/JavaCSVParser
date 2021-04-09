package basic.framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CSVParser {

    private List<List<String>> records;
    private HashMap<String, List<String>> hashmapdata;

    private int rowCount;
    private int columnCount;

    CSVParser() {}

    public void parse(String fileName, String delim) {
        records = new ArrayList<>();
        hashmapdata = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String d = ",";
            while((line = br.readLine()) != null) {
                if(delim != null) {
                    d = delim;
                }
                String[] vals = line.split(d);
                records.add(Arrays.asList(vals));
            }
        } catch(Exception ex) {ex.printStackTrace();}

        columnCount = records.get(0).size();
        rowCount = records.size();
        System.out.println("Parsing file with " + rowCount + " rows and " + columnCount + " columns...");

        for(int i = 0; i < columnCount; i++) {
            ArrayList<String> columnValues = new ArrayList<>();
            for(int j = 1; j < rowCount; j++) {
                try {columnValues.add(records.get(j).get(i)); } catch(Exception ex) {}
            }
            hashmapdata.put(records.get(0).get(i), columnValues);
        }

        System.out.println("DONE");
    }

    @SuppressWarnings("unused")
    public List<List<String>> getRecords() {
        return records;
    }

    public Set<String> getHashMapDataKeys() {
        return hashmapdata.keySet();
    }

    public HashMap<String, List<String>> getHashmapData() {
        return hashmapdata;
    }


}

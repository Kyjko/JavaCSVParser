package basic.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {

    CSVParser parser;

    public Main() {
        parser = new CSVParser();
        parser.parse("C:\\Users\\User\\Desktop\\java_finance_test\\data.csv", null);

        HashMap<String, List<String>> data = parser.getHashmapData();

        Set<String> keys = parser.getHashMapDataKeys();
        System.out.println("----------\nfound keys\n----------");
        for(String s: keys) {
            System.out.println(s);
        }

        // display data
        Display display = new Display();
        display.graph(data, "Data_value", 0, 500);
    }

    public static void main(String[] args) {
        new Main();
    }

}

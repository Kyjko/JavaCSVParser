package basic.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {

    CSVParser parser;

    public Main() {
        parser = new CSVParser("C:\\Users\\User\\Desktop\\java_finance_test\\data.csv");

        HashMap<String, List<String>> data = parser.getHashmapData();

        Set<String> keys = parser.getHashMapDataKeys();
        System.out.println("----------\nfound keys\n----------");
        for(String s: keys) {
            System.out.println(s);
        }

        // test

        Stream<String> keys_1 = data.get("Data_value").stream();
        keys_1.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Main();
    }

}

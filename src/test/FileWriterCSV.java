package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileWriterCSV {
    public static void main(String[] args) throws IOException {
//        List<List<String>> rows = Arrays.asList(
//          Arrays.asList("1","2"), Arrays.asList("1","2"), Arrays.asList("1","2")
//        );

        FileWriter csvWriter = new FileWriter("new.csv");
        csvWriter.append("event");
        csvWriter.append(",");
        csvWriter.append("Rate");
        csvWriter.append("\n");

        for (int i = 0; i < 10; i++) {
            csvWriter.append(String.join(",", Arrays.asList(Integer.toString(i),Integer.toString(i+1))));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}

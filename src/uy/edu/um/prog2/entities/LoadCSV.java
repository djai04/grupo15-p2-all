package uy.edu.um.prog2.entities;

import org.apache.commons.csv.*;
import java.io.*;

public class LoadCSV {
    public LoadCSV() throws IOException {
        Reader in = new FileReader("dataset/f1_dataset_test.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

        int counter = 10;
        for (CSVRecord record : records) {
            String usuario = record.get(1);
            System.out.println(usuario);
            System.out.println("-----------------------");
            counter--;
            if (counter == 0) {
                break;
            }
        }
    }
}

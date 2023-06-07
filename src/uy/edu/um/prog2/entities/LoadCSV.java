package uy.edu.um.prog2.entities;

import org.apache.commons.csv.*;
import java.io.*;

import static java.lang.System.currentTimeMillis;

public class LoadCSV {

    // En el constructor podriamos recorrer la totalidad del CSV, crear los objetos,
    //  e ir insertandolos en las estructuras de datos
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

    // Devuelve la cantidad de CSV records que no son consistentes con la cantidad de columnas
    public static int checkDataConsistency(String csvPath) throws IOException {
        Reader in = new FileReader(csvPath);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        int consistencyCounter = 0;

        for (CSVRecord record : records) {
            if (!record.isConsistent()) {
                consistencyCounter++;
            }
        }

        return consistencyCounter;
    }

    // Devuelve la cantidad de milisegundos que toma recorrer el dataset.
    public static long checkTimeSpent(String csvPath) throws IOException {
        Reader in = new FileReader(csvPath);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

        long timeStart = currentTimeMillis();
        for (CSVRecord record : records) {

        }
        long timeEnd = currentTimeMillis();

        return timeEnd - timeStart;
    }

    // Devuelve la cantidad de records en el CSV.
    public static long checkAmountOfRecords (String csvPath) throws IOException {
        Reader in = new FileReader(csvPath);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

        long amountOfRecords = 0;

        for (CSVRecord record : records) {
            amountOfRecords++;
        }

        return amountOfRecords - 1;
    }
}

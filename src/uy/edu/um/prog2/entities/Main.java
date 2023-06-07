package uy.edu.um.prog2.entities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // LoadCSV data = new LoadCSV();
        System.out.println(LoadCSV.checkAmountOfRecords("dataset/f1_dataset_test.csv"));
    }
}

package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
        LoadCSV data = new LoadCSV();
        HashTable<String, User> userList = new HashTable<>(1000000);
        HashTable<Long, Tweet> tweetList = new HashTable<>(1000000);
        HashTable<Long, Driver> driverList = new HashTable<>(50);
        HashTable<String, Hashtag> hashtagList = new HashTable<>(1000000);

        System.out.println("===== DATA LOAD BEGINNING =====");
        LoadCSV.loadDataIntoList(userList, tweetList, driverList, hashtagList);
        System.out.println("===== DATA LOAD END =====");
        System.out.println(tweetList.length());

        System.out.println("===== QUERY BEGINNING =====");
        System.out.println();
        // Desde el 07/2021 hasta el 08/2022
        Queries.hashtagMasUsadoParaUnDiaDado(tweetList);
        System.out.println("===== QUERY END =====");



    }
}

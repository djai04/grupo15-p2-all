package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LoadCSV data = new LoadCSV();
        HashTable<String, User> userList = new HashTable<>(100000);
        HashTable<Long, Tweet> tweetList = new HashTable<>(100000);
        HashTable<String, Driver> driverList = new HashTable<>(50);

        LoadCSV.loadDataIntoList(userList, tweetList, driverList);

        User givenUser = userList.get("Chris Hawkins");
        System.out.println(givenUser.getUserTweets().get(2).getContent());
    }
}

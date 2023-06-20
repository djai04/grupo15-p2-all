package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
        LoadCSV data = new LoadCSV();
        HashTable<String, User> userList = new HashTable<>(100000);
        HashTable<Long, Tweet> tweetList = new HashTable<>(100000);
        HashTable<Long, Driver> driverList = new HashTable<>(50);
        HashTable<String, Hashtag> hashtagList = new HashTable<>(100000);

        LoadCSV.loadDataIntoList(userList, tweetList, driverList, hashtagList);

        User givenUser = userList.get("Chris Hawkins");
        System.out.println(givenUser.getUserTweets().get(2).getContent());
        System.out.println(givenUser.getUserTweets().get(2).getHashtags().get(0).getTag());
        System.out.println(givenUser.getUserTweets().get(2).getId());

        System.out.println(userList.length());

        System.out.println("=====================");
        System.out.println(driverList.get(19L).getTweetsMentioned().get(0).getContent());
        System.out.println("=====================");
        System.out.println(userList.get("Cecilia Demartini").getAmountOfFavourites());

        Queries.diezPilotosMasMencionados(driverList);

    }
}

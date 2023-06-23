package uy.edu.um.prog2.entities;

import org.apache.commons.csv.*;
import uy.edu.um.prog2.adt.hash.HashMapNode;
import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class LoadCSV {
    public LoadCSV() throws IOException {

    }

    public static void loadDataIntoList(HashTable<String, User> allUsers, HashTable<Long, Tweet> allTweets, HashTable<Long, Driver> allDrivers, HashTable<String, Hashtag> allHashtags) throws IOException {
        Long tiempoInicial= System.currentTimeMillis();
        // Create file
        File file =new File("dataset/drivers.txt");
        Scanner scanner= new Scanner(file);
        // Create drivers
        while(scanner.hasNextLine()){
            String driverName= scanner.nextLine();
            Driver driver=new Driver(driverName);
            allDrivers.put(driver.getId(),driver);
        }
        scanner.close();

        Reader in = new FileReader("dataset/f1_dataset.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        int counter = 0;
        boolean isFirst = true;
        int amountOfRecordsSkipped = 0;

        for (CSVRecord record : records) {
            try {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                String userName = record.get(1);

                String userFavourites = record.get(7);

                String isVerified = record.get(8);
                boolean isVerifiedBoolean;

                String tweetDate = record.get(9);

                String tweetText = record.get(10);

                String tweetHashtags = record.get(11);

                String tweetSource = record.get(12);

                String tweetIsRetweet = record.get(13);
                boolean isRetweetBoolean;

                // Create user
                if (isVerified == "False") {
                    isVerifiedBoolean = false;
                } else {
                    isVerifiedBoolean = true;
                }


                User currentUser = new User(userName, isVerifiedBoolean, Double.valueOf(userFavourites));

                // Create tweet
                //  Validate isRetweet
                if (tweetIsRetweet == "False") {
                    isRetweetBoolean = false;
                } else {
                    isRetweetBoolean = true;
                }

                //  Validate tweetDate
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date tweetDateObject = null;
                tweetDateObject = formatter.parse(tweetDate);

                Tweet currentTweet = new Tweet(tweetText, tweetSource, isRetweetBoolean, tweetDateObject, currentUser);

                //  Validate tweetHashtags
                //  Should be a linked list of type Hashtag
                tweetHashtags = tweetHashtags.replace("[", "").replace("]", "").replace("'", "").replace(" ", "");
                String[] tweetHashtagsInArray = tweetHashtags.split(",");

                for (int i = 0; i < tweetHashtagsInArray.length; i++) {
                    Hashtag currentHashtag =  new Hashtag(tweetHashtagsInArray[i].toLowerCase());

                    if (allHashtags.contains(currentHashtag.getTag())) {
                        currentHashtag = allHashtags.get(currentHashtag.getTag());
                        currentHashtag.getTweets().add(currentTweet);
                    } else {
                        currentHashtag.getTweets().add(currentTweet);
                        allHashtags.put(currentHashtag.getTag(), currentHashtag);
                    }
                    // Debemos argumentar en el readme
                    currentTweet.getHashtags().add(currentHashtag);
                }


                if (allUsers.contains(currentUser.getUsername())) {
                    User foundUser = allUsers.get(currentUser.getUsername());
                    foundUser.getUserTweets().add(currentTweet);
                } else {
                    currentUser.getUserTweets().add(currentTweet);
                    allUsers.put(currentUser.getUsername(), currentUser);
                }

                allTweets.put(currentTweet.getId(), currentTweet);

                //chequeo si en el tweet se menciona al corredor
                LinkedList<Long> keys = allDrivers.getKeys();
                for (int i = 0; i < keys.length(); i++) {
                    Driver driver = allDrivers.get(keys.get(i));
                    if (tweetText.toLowerCase().contains(driver.getDriverName().toLowerCase())) {
                        driver.getTweetsMentioned().add(currentTweet);
                    }
                }

                // Check last tweet date and update user favourites
                if (allUsers.get(currentUser.getUsername()).getLastTweetDate() == null) {
                    allUsers.get(currentUser.getUsername()).setLastTweetDate(currentTweet.getDate());
                } else {
                    if (allUsers.get(currentUser.getUsername()).getLastTweetDate().compareTo(currentTweet.getDate()) < 0) {
                        allUsers.get(currentUser.getUsername()).setLastTweetDate(currentTweet.getDate());
                        allUsers.get(currentUser.getUsername()).setAmountOfFavourites(currentUser.getAmountOfFavourites());
                    }
                }

                counter++;
                if (counter == 1000000) {
                    break;
                }
            } catch (Exception e) {
                amountOfRecordsSkipped++;
                continue;
            }
        }
        System.out.println(amountOfRecordsSkipped);
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);

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

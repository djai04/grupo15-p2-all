package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.heap.MaxHeap;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.util.Scanner;

public class Queries {
    public static void diezPilotosMasMencionados(HashTable<Long, Driver> allDrivers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el mes");
        int month = scanner.nextInt();
        System.out.print("Ingrese el año");
        int year = scanner.nextInt();
        MaxHeap<Integer,Driver> driversByMentions= new MaxHeap<>();
        LinkedList<Long> driverKeys = allDrivers.getKeys();

        for (int i = 0; i < driverKeys.length(); i++) {
            Driver driver =allDrivers.get(driverKeys.get(i));
            //Adding driver to heap using getTweetsMentioned´s size as key
            LinkedList<Tweet> tweetsFiltered = new LinkedList<>();
            for (int j = 0; j <driver.getTweetsMentioned().length() ; j++) {
                Tweet tweetFiltered= driver.getTweetsMentioned().get(j);
                if(tweetFiltered.getYear()==year && tweetFiltered.getMonth()==month){
                    tweetsFiltered.add(tweetFiltered);
                }
            }

            driversByMentions.push(tweetsFiltered.length(), driver);
        }
        for (int i = 0; i < 10; i++) {
            Driver mostMentionedDriver = driversByMentions.pop();
            System.out.println(mostMentionedDriver.getDriverName());
            System.out.println(mostMentionedDriver.getTweetsMentioned().length());
            
        }
    }

    public static void quinceUsuariosConMasTweets(HashTable<String, User> allUsers) {
        MaxHeap<Integer,User> usersByTweets= new MaxHeap<>();
        LinkedList<String> usersKeys = allUsers.getKeys();

        for (int i = 0; i < usersKeys.length(); i++) {
            User user=allUsers.get(usersKeys.get(i));
            usersByTweets.push(user.getUserTweets().length(),user);

        }
        for (int i = 0; i < 15; i++) {
            User topUser=usersByTweets.pop();
            System.out.println(topUser.getUsername());
            System.out.println(topUser.getUserTweets().length());
            if (topUser.isVerified()) {
                System.out.println("Verified");
            } else {
                System.out.println("Not verified");
            }

        }
    }

    public static void sieteCuentasConMasFavoritos(HashTable<String, User> allUsers) {
        MaxHeap<Double, User> usersByFavourites = new MaxHeap<>();
        LinkedList<String> usersKeys = allUsers.getKeys();
        for (int i = 0; i < usersKeys.length(); i++) {
            User user = allUsers.get(usersKeys.get(i));
            usersByFavourites.push(user.getAmountOfFavourites(), user);
        }
        for (int i = 0; i < 7; i++) {
            User topUserFavourite = usersByFavourites.pop();
            System.out.println(topUserFavourite.getUsername());
            System.out.println(topUserFavourite.getAmountOfFavourites());;
        }
    }}
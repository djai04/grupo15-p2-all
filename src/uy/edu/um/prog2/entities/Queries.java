package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.heap.MaxHeap;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Queries {
    public static void diezPilotosMasMencionados(HashTable<Long, Driver> allDrivers) {

        Long tiempoInicial= System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el mes: ");
        int month = scanner.nextInt();
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        MaxHeap<Integer,Driver> driversByMentions= new MaxHeap<>();
        LinkedList<Long> driverKeys = allDrivers.getKeys();

        for (int i = 0; i < driverKeys.length(); i++) {
            Driver driver =allDrivers.get(driverKeys.get(i));
            // Adding driver to heap using getTweetsMentioned´s size as key
            LinkedList<Tweet> tweetsFiltered = new LinkedList<>();
            for (int j = 0; j <driver.getTweetsMentioned().length() ; j++) {
                Tweet tweetFiltered = driver.getTweetsMentioned().get(j);
                int tweetMonth = DateUtils.getMonthFromDate(tweetFiltered.getDate());
                int tweetYear = DateUtils.getYearFromDate(tweetFiltered.getDate());

                if(tweetMonth == month && tweetYear == year) {
                    tweetsFiltered.add(tweetFiltered);
                }
            }
            if (tweetsFiltered.length() != 0) {
                driversByMentions.push(tweetsFiltered.length(), driver);
                // System.out.println(tweetsFiltered.length());
            }
        }

        for (int i = 0; i < 10; i++) {
            int amountOfTweets = driversByMentions.peekKey();
            Driver mostMentionedDriver = driversByMentions.pop();
            System.out.println("===== DRIVER =====");
            System.out.println(mostMentionedDriver.getDriverName());
            System.out.println(amountOfTweets);
            System.out.println();
        }
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);
    }

    public static void quinceUsuariosConMasTweets(HashTable<String, User> allUsers) {
        Long tiempoInicial= System.currentTimeMillis();
        MaxHeap<Integer,User> usersByTweets = new MaxHeap<>();

        /**
        LinkedList<String> usersKeys = allUsers.getKeys();
        for (int i = 0; i < usersKeys.length(); i++) {
            User user = allUsers.get(usersKeys.get(i));
            usersByTweets.push(user.getUserTweets().length(), user);
        }
         **/

        String[] userKeys = allUsers.getKeysIfString(allUsers.length());
        for (int i = 0; i < userKeys.length; i++) {
            User user = allUsers.get(userKeys[i]);
            usersByTweets.push(user.getUserTweets().length(), user);
        }

        for (int i = 0; i < 15; i++) {
            User topUser = usersByTweets.pop();
            System.out.println("===== USER =====");
            System.out.println("Username: " + topUser.getUsername());
            System.out.println("Amount of tweets: " + topUser.getUserTweets().length());
            if (topUser.isVerified()) {
                System.out.println("Verified: true");
            } else {
                System.out.println("Verified: false");
            }
            System.out.println();



        }
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);
    }

    public static void sieteCuentasConMasFavoritos(HashTable<String, User> allUsers) {
        Long tiempoInicial= System.currentTimeMillis();
        MaxHeap<Double, User> usersByFavourites = new MaxHeap<>();

        /**
        LinkedList<String> usersKeys = allUsers.getKeys();
        for (int i = 0; i < usersKeys.length(); i++) {
            User user = allUsers.get(usersKeys.get(i));
            usersByFavourites.push(user.getAmountOfFavourites(), user);
        }
         **/

        String[] userKeys = allUsers.getKeysIfString(allUsers.length());
        for (int i = 0; i < userKeys.length; i++) {
            User user = allUsers.get(userKeys[i]);
            usersByFavourites.push(user.getAmountOfFavourites(), user);
        }

        for (int i = 0; i < 7; i++) {
            User topUserFavourite = usersByFavourites.pop();

            System.out.println("===== USER =====");
            System.out.println("Username: " + topUserFavourite.getUsername());
            System.out.println("Amount of favourites: " + topUserFavourite.getAmountOfFavourites());
            System.out.println();

            Long tiempoFinal=System.currentTimeMillis();
            Long tiempoPasado= tiempoFinal-tiempoInicial;
            System.out.println(tiempoPasado);
        }
    }

    public static void encontrarTweetSegunPalabra(HashTable<Long, Tweet> allTweets) {
        Long tiempoInicial= System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la palabra o frase a buscar: ");
        String phrase = scanner.nextLine();
        long counter = 0;

        /**
        LinkedList<Long> tweetsKeys = allTweets.getKeys();
        for (int i = 0; i < tweetsKeys.length(); i++) {
            Tweet tweet = allTweets.get(tweetsKeys.get(i));
            if (tweet.getContent().contains(phrase)) {
                counter++;
            }
        }
         **/

        Long[] tweetKeys = allTweets.getKeysIfLong(allTweets.length());
        for (int i = 0; i < tweetKeys.length; i++) {
            Tweet tweet = allTweets.get(tweetKeys[i]);
            if (tweet.getContent().toLowerCase().contains(phrase.toLowerCase())) {
                counter++;
            }
        }

        System.out.println("La cantidad de tweets con esa palabra o frase es: " + counter);
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);
    }

    public static void cantidadDeHashtagsDistintosParaUnDiaDado(HashTable<Long, Tweet> allTweets) {
        Long tiempoInicial= System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha en formato YYYY-MM-DD: ");
        String givenDate = scanner.nextLine();

        String[] elements = givenDate.split("-");
        int year = Integer.parseInt(elements[0]);
        int month = Integer.parseInt(elements[1]);
        int day = Integer.parseInt(elements[2]);

        // Podria ser un hash
        LinkedList<Tweet> tweetsFiltered = new LinkedList<>();

        /**
        LinkedList<Long> tweetKeys = allTweets.getKeys();
        for (int i = 0; i < tweetKeys.length(); i++) {
            Tweet tweet = allTweets.get(tweetKeys.get(i));

            int tweetYear = DateUtils.getYearFromDate(tweet.getDate());
            int tweetMonth = DateUtils.getMonthFromDate(tweet.getDate());
            int tweetDay = DateUtils.getDayFromDate(tweet.getDate());

            if (tweetYear == year && tweetMonth == month && tweetDay == day) {
                tweetsFiltered.add(tweet);
            }
        }
         **/

        Long[] tweetsKeys = allTweets.getKeysIfLong(allTweets.length());
        for (int i = 0; i < tweetsKeys.length; i++) {
            Tweet tweet = allTweets.get(tweetsKeys[i]);

            int tweetYear = DateUtils.getYearFromDate(tweet.getDate());
            int tweetMonth = DateUtils.getMonthFromDate(tweet.getDate());
            int tweetDay = DateUtils.getDayFromDate(tweet.getDate());

            if (tweetYear == year && tweetMonth == month && tweetDay == day) {
                tweetsFiltered.add(tweet);
            }
        }

        LinkedList<Hashtag> filteredTweetsHashtags = new LinkedList<>();

        for (int i = 0; i < tweetsFiltered.length(); i++) {
            LinkedList<Hashtag> thisTweetHashtag = tweetsFiltered.get(i).getHashtags();

            for (int j = 0; j < thisTweetHashtag.length(); j++) {
                if (!filteredTweetsHashtags.isInList(thisTweetHashtag.get(j))) {
                    filteredTweetsHashtags.add(thisTweetHashtag.get(j));
                }
            }
        }

        System.out.println("La cantidad de hashtags distintos en el " + givenDate + " es: " + filteredTweetsHashtags.length());
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);
    }

    public static void hashtagMasUsadoParaUnDiaDado(HashTable<Long, Tweet> allTweets) {
        Long tiempoInicial= System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha en formato YYYY-MM-DD: ");
        String givenDate = scanner.nextLine();
        MaxHeap<Long, Hashtag> hashtagsRepeated = new MaxHeap<>();
        String[] elements = givenDate.split("-");
        int year = Integer.parseInt(elements[0]);
        int month = Integer.parseInt(elements[1]);
        int day = Integer.parseInt(elements[2]);

        LinkedList<Tweet> tweetsFiltered = new LinkedList<>();

        /**
        LinkedList<Long> tweetKeys = allTweets.getKeys();
        for (int i = 0; i < tweetKeys.length(); i++) {
            Tweet tweet = allTweets.get(tweetKeys.get(i));

            int tweetYear = DateUtils.getYearFromDate(tweet.getDate());
            int tweetMonth = DateUtils.getMonthFromDate(tweet.getDate());
            int tweetDay = DateUtils.getDayFromDate(tweet.getDate());

            if (tweetYear == year && tweetMonth == month && tweetDay == day) {
                tweetsFiltered.add(tweet);
            }
        }
         **/

        Long[] tweetsKeys = allTweets.getKeysIfLong(allTweets.length());
        for (int i = 0; i < tweetsKeys.length; i++) {
            Tweet tweet = allTweets.get(tweetsKeys[i]);

            int tweetYear = DateUtils.getYearFromDate(tweet.getDate());
            int tweetMonth = DateUtils.getMonthFromDate(tweet.getDate());
            int tweetDay = DateUtils.getDayFromDate(tweet.getDate());

            if (tweetYear == year && tweetMonth == month && tweetDay == day) {
                tweetsFiltered.add(tweet);
            }
        }

        HashTable<String,Long> hashtags = new HashTable<>();

        for (int i = 0; i < tweetsFiltered.length(); i++) {
            //voy a cada tweet de los tweets filtrados
            Tweet tweet = tweetsFiltered.get(i);
            //Recorro la lista de hasthtags del tweet
            for (int j = 0; j < tweet.getHashtags().length(); j++) {
                //Me paro en cada Hashtag
                Hashtag hashtag = tweet.getHashtags().get(j);
                // Me pregunto si mi hashTable tiene ese hashtag
                if(!hashtags.contains(hashtag.getTag())){
                    //Lo agrego con Long=1 porque es la primera vez que aparece
                    hashtags.put(hashtag.getTag(), 1L);
                } else{
                    Long amountRepeated = hashtags.get(hashtag.getTag());
                    hashtags.remove(hashtag.getTag());
                    hashtags.put(hashtag.getTag(), amountRepeated + 1);
                }
            }
        }

        MaxHeap<Long, String> mostRepeatingHashtags = new MaxHeap<>();
        LinkedList<String> hashtagKeys = hashtags.getKeys();

        for (int i = 0; i < hashtagKeys.length(); i++) {
            mostRepeatingHashtags.push(hashtags.get(hashtagKeys.get(i)), hashtagKeys.get(i));
        }

        String mostRepeatingHashtag = mostRepeatingHashtags.pop();

        if (mostRepeatingHashtag == null) {
            System.out.println("No hubo hashtags en el dia dado.");
        }

        if (mostRepeatingHashtag.equals("f1")) {
            System.out.println("El hashtag que mas se repite en este dia es: " + mostRepeatingHashtags.pop());
        } else {
            System.out.println("El hashtag que mas se repite en este dia es: " + mostRepeatingHashtag);
        }

        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoPasado= tiempoFinal-tiempoInicial;
        System.out.println(tiempoPasado);

    }

}
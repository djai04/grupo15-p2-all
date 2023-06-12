package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class User {

    private String username;

    private boolean isVerified;

    private long amountOfFavourites;

    private LinkedList<Tweet> userTweets;

    public LinkedList<Tweet> getUserTweets() {
        return userTweets;
    }

    public void setUserTweets(LinkedList<Tweet> userTweets) {
        this.userTweets = userTweets;
    }


    public User(String username, boolean isVerified, long amountOfFavourites) {
        this.username = username;
        this.isVerified = isVerified;
        this.amountOfFavourites = amountOfFavourites;
        this.userTweets = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public long getAmountOfFavourites() {
        return amountOfFavourites;
    }

    public void setAmountOfFavourites(long amountOfFavourites) {
        this.amountOfFavourites = amountOfFavourites;
    }
}

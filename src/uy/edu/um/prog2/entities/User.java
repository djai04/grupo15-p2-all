package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.util.Date;

public class User {

    private String username;

    private boolean isVerified;

    private double amountOfFavourites;

    private LinkedList<Tweet> userTweets;
    private Date lastTweetDate;

    public LinkedList<Tweet> getUserTweets() {
        return userTweets;
    }

    public void setUserTweets(LinkedList<Tweet> userTweets) {
        this.userTweets = userTweets;
    }


    public User(String username, boolean isVerified, double amountOfFavourites) {
        this.username = username;
        this.isVerified = isVerified;
        this.amountOfFavourites = amountOfFavourites;
        this.userTweets = new LinkedList<>();
        this.lastTweetDate = null;
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

    public double getAmountOfFavourites() {
        return amountOfFavourites;
    }

    public void setAmountOfFavourites(long amountOfFavourites) {
        this.amountOfFavourites = amountOfFavourites;
    }

    public void setAmountOfFavourites(double amountOfFavourites) {
        this.amountOfFavourites = amountOfFavourites;
    }

    public Date getLastTweetDate() {
        return lastTweetDate;
    }

    public void setLastTweetDate(Date lastTweetDate) {
        this.lastTweetDate = lastTweetDate;
    }
}

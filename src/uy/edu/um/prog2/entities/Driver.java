package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Driver {

    private long id;

    private String driverName;

    private LinkedList<Tweet> tweetsMentioned;

    public LinkedList<Tweet> getTweetsMentioned() {
        return tweetsMentioned;
    }

    public void setTweetsMentioned(LinkedList<Tweet> tweetsMentioned) {
        this.tweetsMentioned = tweetsMentioned;
    }


    public Driver(long id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.tweetsMentioned= new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}

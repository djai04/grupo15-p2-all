package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Driver {

    private long id;

    private String driverName;

    private LinkedList<Tweet> tweetsMentioned;
    private static long nextId = 1;

    public LinkedList<Tweet> getTweetsMentioned() {
        return tweetsMentioned;
    }

    public void setTweetsMentioned(LinkedList<Tweet> tweetsMentioned) {
        this.tweetsMentioned = tweetsMentioned;
    }


    public Driver(String driverName) {
        this.id = nextId;
        this.driverName = driverName;
        this.tweetsMentioned= new LinkedList<>();

        nextId++;
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

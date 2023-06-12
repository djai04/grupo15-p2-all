package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Hashtag {

    private long id;

    private String text;

    private LinkedList<Tweet> tweets;

    public LinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(LinkedList<Tweet> tweets) {
        this.tweets = tweets;
    }


    public Hashtag(long id, String text) {
        this.id = id;
        this.text = text;
        this.tweets= new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

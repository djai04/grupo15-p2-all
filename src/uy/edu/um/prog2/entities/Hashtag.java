package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Hashtag {

    private String tag;

    private LinkedList<Tweet> tweets;

    public Hashtag(String tag) {
        this.tag = tag;
        this.tweets= new LinkedList<>();
    }

    public LinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(LinkedList<Tweet> tweets) {
        this.tweets = tweets;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}

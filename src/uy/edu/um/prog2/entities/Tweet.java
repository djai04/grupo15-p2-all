package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.util.Date;

public class Tweet {
    private static long nextID = 1;
    private long id;
    private String content;
    private String source;
    private boolean isRetweet;
    private Date date;

    private User author;
    private LinkedList<Hashtag> hashtags;


    public LinkedList<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(LinkedList<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    public Tweet(String content, String source, boolean isRetweet, Date date, User author) {
        this.id = nextID;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.date = date;
        this.author=author;
        this.hashtags= new LinkedList<>();
        nextID++;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public Date getDate() {
        return date;
    }

    public int getYear() {
        return this.date.getYear() + 1900;
    }

    public int getMonth() {
        return this.date.getMonth() + 1;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}

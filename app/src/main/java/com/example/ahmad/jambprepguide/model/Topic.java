package com.example.ahmad.jambprepguide.model;

/**
 * Created by ahmad on 4/1/17.
 */

public class Topic {
    private int id;
    private Subject subject;
    private String title;

    public Topic(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

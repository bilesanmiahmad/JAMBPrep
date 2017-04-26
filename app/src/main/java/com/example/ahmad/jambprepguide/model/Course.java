package com.example.ahmad.jambprepguide.model;

/**
 * Created by ahmad on 4/1/17.
 */

public class Course {
    private int id;
    private String title;

    public Course(String title) {
        this.title = title;
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

package com.example.ahmad.jambprepguide.model;

/**
 * Created by ahmad on 4/1/17.
 */

public class Institution {
    private int id;
    private String title;
    private String address;

    public Institution(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

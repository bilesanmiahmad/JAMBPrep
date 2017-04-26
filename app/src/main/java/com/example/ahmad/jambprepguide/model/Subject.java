package com.example.ahmad.jambprepguide.model;

import java.util.ArrayList;

/**
 * Created by ahmad on 3/9/17.
 */

public class Subject {
    private String title;
    private boolean selected;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Subject(String title) {
        this.title = title;
    }

    public boolean isSelected(){ return selected;}

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

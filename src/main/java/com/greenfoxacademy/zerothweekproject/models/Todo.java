package com.greenfoxacademy.zerothweekproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private boolean urgent = false;
    private boolean done = false;

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    public Todo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (!this.done) {
            return this.id + " - " + " [ ] " + this.title;
        }
        else{
            return this.id + " - " + " [x] " + this.title;
        }
    }
}

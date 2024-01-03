package com.example.searchengineproj;

import javafx.scene.image.Image;

import java.util.*;

public class User {
    private final String username;
    private String password;
    private Image profile;
    private List<String> bookmark ;

    public User(String username) {
        this.username = username;
        bookmark = new ArrayList<>();

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getProfile() {
        return profile;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public List<String> getBookmark() {
        return bookmark;
    }
    public void setBookmark(List<String> bookmark){
        this.bookmark = bookmark;
    }
    public void addBookMark(String z){
        this.bookmark.add(z);    }



    @Override
    public String toString() {
        return this.username + " " + this.password;
    }
}

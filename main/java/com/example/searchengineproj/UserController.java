package com.example.searchengineproj;

import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class UserController {

    public static final List<User> users = new ArrayList<>();
    private static final List<List<String>> bookmarks = new ArrayList<>();
    public UserController(){

    }

    public static void register(String username , String password){
        User user = new User(username);
        user.setPassword(password);
        user.setProfile(new Image("profile.jpeg"));
        users.add(user);
        SQLConnector sql = new SQLConnector();
        String sqlCmd = String.format("INSERT INTO users (username , password) VALUES ('%s','%s')",username,password);
        //sql.ExecuteSql(sqlCmd);
    }

    public User login(String username,String password) throws SQLException, ClassNotFoundException {
       SQLConnector sql = new SQLConnector();
       String query = String.format("SELECT * FROM users WHERE users.username = '%s'" + "AND users.password = '%s'",username,password);
        ResultSet rs = sql.executeQuery(query);
        User user = null;
        while(rs.next()){
            user = new User(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBookmark(findBookmark(rs.getInt("history")));

        }

        return user;

    }

    public List<String> findBookmark(int index){
        return bookmarks.get(index);
    }



}

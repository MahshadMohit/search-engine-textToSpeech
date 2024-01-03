package com.example.searchengineproj;

import java.sql.*;
public class SQLConnector {

    private final String URL = "jdbc:mysql://localhost/players";
    private final String username = "Asoo";
    private final String password = "password1212";
    public SQLConnector(){}

    void ExecuteSql(String sqlCmd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con  = DriverManager.getConnection(URL,username,password);
            Statement s = con.prepareStatement(sqlCmd);
            //s.execute(sqlCmd);
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    void showDataBase(String sqlCmd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, username, password);
            Statement s = con.prepareStatement(sqlCmd);
            ResultSet resultSet = s.executeQuery(sqlCmd);
            while(resultSet.next()){
                System.out.println(resultSet.getString("username"));
                System.out.println();
                System.out.println(resultSet.getString("password"));

            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL,username , password);
        Statement statement = connection.prepareStatement(query);
        return statement.executeQuery(query);
    }


}

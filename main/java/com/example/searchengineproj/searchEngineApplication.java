package com.example.searchengineproj;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.*;



import java.io.IOException;

public class searchEngineApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(searchEngineApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 805, 506);
        stage.setTitle("ISearch");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("searchIcon.png"));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        SearchEngineFunctions sf = new SearchEngineFunctions();
        sf.buildFile();



    }
}
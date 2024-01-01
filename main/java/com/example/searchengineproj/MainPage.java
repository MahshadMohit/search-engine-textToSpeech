package com.example.searchengineproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    private Button goBtn;

    @FXML
    private Button login;

    @FXML
    private ImageView profile;

    @FXML
    private Button register;

    @FXML
    private TextField textField;

    @FXML
    private Text userHi;
    public static String str;

    public void setGoBtn(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(searchEngineApplication.class.getResource("searchPage.fxml"));
        str = textField.getText();
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

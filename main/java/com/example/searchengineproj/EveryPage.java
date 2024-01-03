package com.example.searchengineproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EveryPage implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TextArea textArea;
    @FXML
    private ImageView save;
    public static String str;
    public static User user;


    public void setBack(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(searchEngineApplication.class.getResource("searchPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setSave(){
        user.addBookMark(str);
        SQLConnector sql = new SQLConnector();
        String sqlCmd = String.format("UPDATE users SET history = '%s' WHERE username = '%s'",str,user.getUsername());
        //sql.ExecuteSql(sqlCmd);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("your history ");
        alert.setContentText(user.getBookmark().toString());

        Optional<ButtonType> result = alert.showAndWait();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setVisible(true);
        textArea.setDisable(false);
        textArea.setWrapText(true);
        textArea.setScrollLeft(10);
        textArea.setEditable(false);


        try {
            textArea.setText(String.valueOf(SearchEngineFunctions.textOfEeachFile(str)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

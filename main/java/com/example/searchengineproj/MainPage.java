package com.example.searchengineproj;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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
    private AnchorPane pane;

    @FXML
    private TextField textField;

    @FXML
    private Text userHi;

    @FXML
    private Rectangle rec;
    @FXML
    private Text text;
    public static String str;
    public static User user;

    public void setGoBtn(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(searchEngineApplication.class.getResource("searchPage.fxml"));
        str = textField.getText();
        if (user == null) {
            user = new User("temp");
            UserController.register(user.getUsername(), "temp");
        }
        SearchPage.user = user;
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setRegister() {
        forUser();
    }

    public void setLogin() {
        forLogin();
    }



    public void forUser() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Register");
        dialog.setHeaderText("set your username and password");
        ButtonType login = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        TextField password = new TextField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(login);
        loginButton.setDisable(true);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == login) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            user = new User(usernamePassword.getKey());
            user.setPassword(usernamePassword.getValue());
            user.setBookmark(new ArrayList<>());
            UserController.register(user.getUsername(), user.getPassword());


            userHi.setText("Hi " + usernamePassword.getKey());

        });

    }
    public void forLogin() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("set your username and password");

        ButtonType login = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        TextField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(login);
        loginButton.setDisable(true);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == login) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            //user = new User(usernamePassword.getKey());
            //user.setPassword(usernamePassword.getValue());
            try {
                user = UserController.login(usernamePassword.getKey(),usernamePassword.getValue());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            userHi.setText("Hi " + usernamePassword.getKey());

        });
    }

    public void setEffectOnSearchBar() {
        textField.setEffect(null);
        textField.setEffect(setDrop());

    }

    public void setNoEffectRec() {
        textField.setEffect(null);
    }

    public void setEffect() {
        text.setEffect(setDrop());
    }

    public void setNoEffect() {
        text.setEffect(null);
        userHi.setEffect(null);
    }

    public DropShadow setDrop() {
        DropShadow ds = new DropShadow();
        ds.setColor(Color.web("#514b4b", 1));
        ds.setWidth(21);
        ds.setHeight(21);
        return ds;
    }

    public void setEffectOnUserHi() {
        userHi.setEffect(setDrop());
    }

    public void setProfileFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extFilterJPG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profile.setImage(image);
            user.setProfile(profile.getImage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user == null) {
            profile.setVisible(true);
            profile.setImage(new Image("profile.jpeg"));
            userHi.setText("Hi ! Please login or signup :)");
            userHi.setVisible(true);
        }
    }
}

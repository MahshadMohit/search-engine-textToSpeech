package com.example.searchengineproj;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class searchEngineController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button button;

    @FXML
    protected void onHelloButtonClick() {
        final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
        fadeIn.setNode(button);
        fadeIn.setToValue(1);
        button.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
        fadeOut.setNode(button);
        fadeOut.setToValue(0.5);
        button.setOnMouseExited(e -> fadeOut.playFromStart());

        button.setOpacity(0.5);


        button.setOpacity(0.5);

    }


}
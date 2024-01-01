package com.example.searchengineproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SearchPage implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button goBtn;

    @FXML
    private Button next;

    @FXML
    private Text t1;

    @FXML
    private Text t11;

    @FXML
    private Text t111;

    @FXML
    private Text t112;

    @FXML
    private Text t113;

    @FXML
    private Text t114;

    @FXML
    private Text t2;

    @FXML
    private Text t3;

    @FXML
    private Text t4;

    @FXML
    private Text t5;
    @FXML
    private Button btn;

    @FXML
    private TextField textField = new TextField();
    private int count = 0;
    public SearchEngineFunctions sf = new SearchEngineFunctions();
    public List<String> list;
    public static StringBuilder sb = new StringBuilder();
    public static String nameOfFile;


    public void setGoBtn() {
        list = new ArrayList<>(1000);
        list = sf.findDoc(textField.getText());
        if (list.isEmpty()) {
            list = sf.findSimilarDocs(textField.getText());
        }
        setList(0);
        count++;


    }

    public void setList(int n) {
        t11.setText("Doc no : " + list.get(n));
        t111.setText("Doc no : " + list.get(n + 1));
        t112.setText("Doc no : " + list.get(n + 2));
        t113.setText("Doc no : " + list.get(n + 3));
        t114.setText("Doc no : " + list.get(n + 4));
        t11.setUnderline(true);
        t111.setUnderline(true);
        t112.setUnderline(true);
        t113.setUnderline(true);
        t114.setUnderline(true);
        t1.setText(SearchEngineFunctions.firstLines.get(n).toLowerCase().substring(0, 20));
        t2.setText(SearchEngineFunctions.firstLines.get(n + 1).toLowerCase().substring(0, 20));
        t3.setText(SearchEngineFunctions.firstLines.get(n + 2).toLowerCase().substring(0, 20));
        t4.setText(SearchEngineFunctions.firstLines.get(n + 3).toLowerCase().substring(0, 20));
        t5.setText(SearchEngineFunctions.firstLines.get(n + 4).toLowerCase().substring(0, 20));
    }


    public void setNext() {
        setList(count);
        count++;

    }

    public void setT1(ActionEvent e) throws IOException {
        EveryPage.str = t11.getText().substring(9);
        setGoEveryPage(e);
    }
    public void setT111(ActionEvent e) throws IOException{
        EveryPage.str = t111.getText().substring(9);
        setGoEveryPage(e);
    }
    public void setT112(ActionEvent e) throws IOException{
        EveryPage.str = t112.getText().substring(9);
        setGoEveryPage(e);
    }
    public void setT113(ActionEvent e) throws IOException{
        EveryPage.str = t113.getText().substring(9);
        setGoEveryPage(e);
    }
    public void setT114(ActionEvent e)throws IOException{
        EveryPage.str = t114.getText().substring(9);
        setGoEveryPage(e);
    }


    public void setGoEveryPage(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(searchEngineApplication.class.getResource("everyPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setText(MainPage.str);
        setGoBtn();

    }
}

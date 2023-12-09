package com.volans.volansyeristasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller1 {
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private Label welcomeText;

    @FXML
    private Button loraButton;

    @FXML
    private Button roketButton;

    @FXML
    private Button dataButton;

    @FXML
    private TextArea log;

    @FXML
    private TextArea PairityBits;

    @FXML
    private Button testButton;

    @FXML
    protected void onTestButtonClick(String i) {
        log.appendText("\nTest Buttonuna tıkladınız.");
    }



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onTestButtonClick() {
        System.out.println("Test butonuna tıkladın.");
    }


    @FXML
    protected void onLoraButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}
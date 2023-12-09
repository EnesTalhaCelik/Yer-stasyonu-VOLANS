package com.volans.volansyeristasyonu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fazecast.jSerialComm.SerialPort;



public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainArayuz.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Yer Istasyonu Test");
        stage.setScene(scene);
        stage.show();

    }




    public static void main(String[] args) {
        launch();


        }



    }

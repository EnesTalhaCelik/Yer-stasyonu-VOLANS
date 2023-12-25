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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.fazecast.jSerialComm.SerialPort;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(mainApplication.class.getResource("mainArayuz.fxml"));
        Parent root = fxmlLoader.load();
        Controller1 controller = fxmlLoader.getController();


        stage.setScene(new Scene(root));
        stage.setTitle("Yer Istasyonu Test");

        stage.show();

    }


    public static void main(String[] args) {

        //System.out.println(Arrays.toString(veriler.getAktarilacakveriler()));

        launch();

        }



    }

package com.volans.volansyeristasyonu;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.web.WebView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.Arrays;

import static com.volans.volansyeristasyonu.veriler.*;

public class Controller1 {
    private Stage stage;
    private Scene scene;
    private Parent root;




    static ObservableList<Integer> boudRates;
    static ObservableList<String> portNames;

    static ObservableList<Integer> pairityTypes = FXCollections.observableArrayList(SerialPort.NO_PARITY,SerialPort.EVEN_PARITY,SerialPort.ODD_PARITY,
                                                                                    SerialPort.MARK_PARITY,SerialPort.SPACE_PARITY);

    static ObservableList<Integer> dataBits = FXCollections.observableArrayList(5,6,7,8);
    static ObservableList<Integer> stopBits = FXCollections.observableArrayList(0,1,2,3);


    @FXML
    private WebView haritaTest;

    private WebEngine engine;

    @FXML
    private TextArea log;

    @FXML
    private ComboBox<String> comboHYIComPort;

    @FXML
    private ComboBox<Integer> comboHYIBoudRate;

    @FXML
    private ComboBox<Integer> comboHYIDataBits;

    @FXML
    private ComboBox<Integer> comboHYIStopBits;

    @FXML
    private ComboBox<Integer> comboHYIPairityBits;

    @FXML
    private Button HYIBaglan;

    @FXML
    private ComboBox<String> comboLoraComPort;

    @FXML
    private ComboBox<Integer> comboLoraBoudRate;

    @FXML
    private ComboBox<Integer> comboLoraDataBits;

    @FXML
    private ComboBox<Integer> comboLoraStopBits;

    @FXML
    private ComboBox<Integer> comboLoraPairityBits;

    @FXML
    private Button LoraBaglan;





    @FXML
    protected void onTestButtonClick() {
        System.out.println("Test butonuna tıkladın.");
        log.appendText("\nTest Buttonuna tıkladınız.");
        arrayFloatToByte();
        System.out.println(Arrays.toString(veriler.getAktarilacakveriler()));

        seriHaberleşme.SerialPortConfiguration config = new seriHaberleşme.SerialPortConfiguration();
        config.setBaudRate(9600);
        config.setNumDataBits(8);
        config.setNumStopBits(1);
        config.setParity(SerialPort.NO_PARITY);

        seriHaberleşme.writeByteToSerialPort("COM12", (byte) 65, config);


    }

    private ObservableList<String> getAvailablePorts() {
        ObservableList<String> portNames = FXCollections.observableArrayList();
        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port : ports) {
            portNames.add(port.getSystemPortName());
            System.out.println(port.getPortDescription());
        }

        return portNames;
    }

    //program başlatıldığında otomatik çağırılır.

    public void initialize() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        log.appendText( formattedDateTime+ " :: Bu Bir Test Mesajıdır ");
        portNames = getAvailablePorts();
        boudRates = FXCollections.observableArrayList(110, 300, 600, 1200, 2400, 4800, 9600,
                     14400, 19200, 38400, 57600, 115200, 128000 , 256000);



        comboHYIComPort.setItems(portNames);
        comboHYIBoudRate.setItems(boudRates);
        comboHYIDataBits.setItems(dataBits);
        comboHYIStopBits.setItems(stopBits);
        comboHYIPairityBits.setItems(pairityTypes);

        comboLoraComPort.setItems(portNames);
        comboLoraBoudRate.setItems(boudRates);
        comboLoraDataBits.setItems(dataBits);
        comboLoraStopBits.setItems(stopBits);
        comboLoraPairityBits.setItems(pairityTypes);

        haritaTest.getEngine().load("https://master.apis.dev.openstreetmap.org/#map=6/39.031/35.252");


        veriler.setAktarilacakverilerINIT();

    }

    public void onHYIPortSelected() {

        String selectedPort = comboHYIComPort.getSelectionModel().getSelectedItem();
        System.out.println("Seçilen port: " + selectedPort);
        HYISP.setPortName(selectedPort);
    }

    public void onHYIBoudRateSelected() {

        Integer selectedHYIBoudRate = comboHYIBoudRate.getSelectionModel().getSelectedItem();
        System.out.println("HYI İle kurulacak Boud Hızı " + selectedHYIBoudRate);
        HYISP.setBaudRate(selectedHYIBoudRate);
    }

    public void onHYIDataBitsSelected() {

        Integer selectedHYIDataBits = comboHYIDataBits.getSelectionModel().getSelectedItem();
        HYISP.setNumDataBits(selectedHYIDataBits);
        System.out.println("HYI veri aktaramında kıllanılaca data bit miktarı " + selectedHYIDataBits);

    }
    public void onHYIStopBitsSelected() {

        Integer selectedHYIStopBits = comboHYIStopBits.getSelectionModel().getSelectedItem();
        HYISP.setNumDataBits(selectedHYIStopBits);
        System.out.println("HYI veri aktaramında kıllanılaca stop bit miktarı " + selectedHYIStopBits);

    }
    public void onHYIPairityBitsSelected() {

        Integer selectedHYIPairityBits = comboHYIPairityBits.getSelectionModel().getSelectedItem();
        HYISP.setParity(selectedHYIPairityBits);
        System.out.println("HYI veri aktaramında kıllanılaca pairity metodu  " + selectedHYIPairityBits);

    }

    //---
    public void onLoraPortSelected() {

        String selectedPort = comboLoraComPort.getSelectionModel().getSelectedItem();
        System.out.println("Lora Seçilen port: " + selectedPort);
        LoraSP.setPortName(selectedPort);
    }

    public void onLoraBoudRateSelected() {

        Integer selectedLoraBoudRate = comboLoraBoudRate.getSelectionModel().getSelectedItem();
        System.out.println("Lora İle kurulacak Boud Hızı " + selectedLoraBoudRate);
        LoraSP.setBaudRate(selectedLoraBoudRate);
    }

    public void onLoraDataBitsSelected() {

        Integer selectedLoraDataBits = comboLoraDataBits.getSelectionModel().getSelectedItem();
        LoraSP.setNumDataBits(selectedLoraDataBits);
        System.out.println("Lora veri aktaramında kıllanılaca data bit miktarı " + selectedLoraDataBits);

    }
    public void onLoraStopBitsSelected() {

        Integer selectedLoraStopBits = comboLoraStopBits.getSelectionModel().getSelectedItem();
        LoraSP.setNumDataBits(selectedLoraStopBits);
        System.out.println("Lora veri aktaramında kıllanılaca stop bit miktarı " + selectedLoraStopBits);

    }
    public void onLoraPairityBitsSelected() {

        Integer selectedLoraPairityBits = comboLoraPairityBits.getSelectionModel().getSelectedItem();
        LoraSP.setParity(selectedLoraPairityBits);
        System.out.println("Lora veri aktaramında kıllanılaca pairity metodu  " + selectedLoraPairityBits);

    }





}

package com.volans.volansyeristasyonu;

import com.fazecast.jSerialComm.SerialPort;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.web.WebView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Arrays;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;


import static com.volans.volansyeristasyonu.veriler.*;

public class Controller1 {




    private Stage stage;
    private Scene scene;
    private Parent root;

    private SerialPort selectedPort1;
    private SerialPort selectedPort2;

    XYChart.Series irtifaGraf = new XYChart.Series();
    XYChart.Series ivmeXGraf = new XYChart.Series();
    XYChart.Series ivmeYGraf = new XYChart.Series();
    XYChart.Series ivmeZGraf = new XYChart.Series();
    XYChart.Series basincGraf = new XYChart.Series();

    static ObservableList<Integer> boudRates;
    static ObservableList<String> portNames;
    private static Integer counter = 0;



    static ObservableList<String> ArduinoListe =  FXCollections.observableArrayList("Sadece Kırmızıyı Yak","Sadece Sarıyı Yak","Sadece Maviyi Yak","Sırayla yanıp sönme","Strobe Efekti",
                                                                                                            "Nefes Alma","Sönme","Test 1");;

    static ObservableList<Integer> pairityTypes = FXCollections.observableArrayList(SerialPort.NO_PARITY,SerialPort.EVEN_PARITY,SerialPort.ODD_PARITY,
                                                                                    SerialPort.MARK_PARITY,SerialPort.SPACE_PARITY);

    static ObservableList<Integer> dataBits = FXCollections.observableArrayList(5,6,7,8);
    static ObservableList<Integer> stopBits = FXCollections.observableArrayList(0,1,2,3);


    @FXML
    private Button serialtestPortuKapat;
    private WebEngine engine;


    @FXML
    private Pane RoketPane;

    @FXML
    private Pane GorevYukuPane;

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
    private ComboBox<String> ArduinoTest;

    @FXML
    private Button ArduinoButton;

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
    private LineChart<?,?> irtifaChart;

    @FXML
    private LineChart<?,?> ivmeXChart;

    @FXML
    private LineChart<?,?> ivmeYChart;

    @FXML
    private LineChart<?,?> ivmeZChart;

    @FXML
    private LineChart<?,?> basncChart;
    public static String logtext;

    private int HYIbaglanDurum = 0;
    private int LorabaglanDurum = 0;
    void setPanePosition(Pane pane,float posX, float posY){
        pane.setTranslateX(posX);
        pane.setTranslateY(posY);
    }

    private float secondsElapsed = 0.0f;
    private Timeline timeline;

    final int[] second = {0};

    Timer timer = new Timer(true);


    public void grafikcizdir(Integer ctr){


        irtifaGraf.getData().add(new XYChart.Data(ctr.toString(),(ctr-15)*(ctr-15)));
        irtifaChart.getData().add(irtifaGraf);

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // Code to be executed every second
            counter++;
            System.out.println("Current value: " + counter);

        }
    };


    @FXML
        protected void roketUcurButton() {

        timer.scheduleAtFixedRate(task, 0, 1000);
        ivmeYGraf.getData().add(new XYChart.Data("0",225));
        ivmeYGraf.getData().add(new XYChart.Data("1",196));
        ivmeYGraf.getData().add(new XYChart.Data("2",169));
        ivmeYGraf.getData().add(new XYChart.Data("3",144));
        ivmeYGraf.getData().add(new XYChart.Data("3",144));
        ivmeYGraf.getData().add(new XYChart.Data("4",121));
        ivmeYGraf.getData().add(new XYChart.Data("5",100));
        ivmeYGraf.getData().add(new XYChart.Data("6",81));
        ivmeYGraf.getData().add(new XYChart.Data("7",64));
        ivmeYGraf.getData().add(new XYChart.Data("8",49));
        ivmeYGraf.getData().add(new XYChart.Data("9",36));
        ivmeYGraf.getData().add(new XYChart.Data("10",25));
        ivmeYGraf.getData().add(new XYChart.Data("11",16));
        ivmeYGraf.getData().add(new XYChart.Data("12",9));
        ivmeYGraf.getData().add(new XYChart.Data("13",4));
        ivmeYGraf.getData().add(new XYChart.Data("14",1));
        ivmeYGraf.getData().add(new XYChart.Data("15",0));
        ivmeYGraf.getData().add(new XYChart.Data("16",-9.6));
        ivmeYGraf.getData().add(new XYChart.Data("17",-9.6));
        ivmeYGraf.getData().add(new XYChart.Data("18",0));
        ivmeYGraf.getData().add(new XYChart.Data("25",0));

        ivmeXGraf.getData().add(new XYChart.Data("0",0.1));
        ivmeXGraf.getData().add(new XYChart.Data("25",0.1));

        ivmeZGraf.getData().add(new XYChart.Data("0",0.1));
        ivmeZGraf.getData().add(new XYChart.Data("25",0.1));

        basincGraf.getData().add(new XYChart.Data("0",1));
        basincGraf.getData().add(new XYChart.Data("25",0.67));

        irtifaGraf.getData().add(new XYChart.Data("0",0));
        irtifaGraf.getData().add(new XYChart.Data("1",107));
        irtifaGraf.getData().add(new XYChart.Data("2",411));
        irtifaGraf.getData().add(new XYChart.Data("3",880));
        irtifaGraf.getData().add(new XYChart.Data("4",1501));
        irtifaGraf.getData().add(new XYChart.Data("5",2239));
        irtifaGraf.getData().add(new XYChart.Data("6",3078));
        irtifaGraf.getData().add(new XYChart.Data("7",3997));
        irtifaGraf.getData().add(new XYChart.Data("8",4500));
        irtifaGraf.getData().add(new XYChart.Data("9",5900));
        irtifaGraf.getData().add(new XYChart.Data("10",6842));
        irtifaGraf.getData().add(new XYChart.Data("11",7800));
        irtifaGraf.getData().add(new XYChart.Data("12",8400));
        irtifaGraf.getData().add(new XYChart.Data("13",9000));
        irtifaGraf.getData().add(new XYChart.Data("14",9100));
        irtifaGraf.getData().add(new XYChart.Data("15",9150));
        irtifaGraf.getData().add(new XYChart.Data("16",9145));
        irtifaGraf.getData().add(new XYChart.Data("17",9130));
        irtifaGraf.getData().add(new XYChart.Data("18",9105));
        irtifaGraf.getData().add(new XYChart.Data("19",9070));
        irtifaGraf.getData().add(new XYChart.Data("20",9020));
        irtifaGraf.getData().add(new XYChart.Data("65",7000));




        irtifaChart.getData().add(irtifaGraf);


        ivmeYChart.getData().add(ivmeYGraf);
        ivmeXChart.getData().add(ivmeXGraf);
        ivmeZChart.getData().add(ivmeZGraf);
        basncChart.getData().add(basincGraf);



        ivmeYChart.getData().add(ivmeYGraf);


        // Cancel the timer after 10 seconds
        //timer.cancel();


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


        //Hyı İçin port açıyoruz
    public void openSelectedPort() {
        if(HYIbaglanDurum == 0){

            String selectedPortName = comboHYIComPort.getValue();
            if (selectedPortName == null || selectedPortName.isEmpty()) {
                System.out.println("Lütfen bir port seçiniz");
                return;
            }

            selectedPort1 = SerialPort.getCommPort(selectedPortName);
            if (selectedPort1.openPort()) {
                System.out.println("port başarıyla açıldı " + selectedPort1.getSystemPortName());
                HYIbaglanDurum = 1;
                HYIBaglan.setText("Portu Kapat");
                HYISP.setPortName(selectedPort1.getSystemPortName());
            } else {
                System.out.println(selectedPortName + "portu açılamadı");
            }
        } else if (HYIbaglanDurum == 1) {
            selectedPort1.closePort();
            HYIbaglanDurum = 0;
            HYIBaglan.setText("Bağlan");
            System.out.println(selectedPort1.getSystemPortName() + "portu kapatıldı");
        }

    }

    public void openLoraSelectedPort() {
        if(LorabaglanDurum == 0){

            String selectedPortName = comboLoraComPort.getValue();
            if (selectedPortName == null || selectedPortName.isEmpty()) {
                System.out.println("Lütfen bir port seçiniz");
                return;
            }

            selectedPort2 = SerialPort.getCommPort(selectedPortName);
            if (selectedPort2.openPort()) {
                System.out.println("port başarıyla açıldı " + selectedPort2.getSystemPortName());
                LorabaglanDurum = 1;
                LoraBaglan.setText("Portu Kapat");
                LoraSP.setPortName(selectedPort2.getSystemPortName());

            } else {
                System.out.println(selectedPortName + "portu açılamadı");
            }
        } else if (LorabaglanDurum == 1) {
            selectedPort2.closePort();
            LorabaglanDurum = 0;
            LoraBaglan.setText("Bağlan");
            System.out.println(selectedPort2.getSystemPortName() + "portu kapatıldı");
        }

    }




    public void HYITIK (){
        portNames = getAvailablePorts();
        comboHYIComPort.setItems(portNames);

        System.out.println("Portlar güncellendi");

    }

    private void sendCommandToArduino() {
        if (selectedPort1 != null && selectedPort1.isOpen()) {
            // Example: Send '1' to turn on LED, '0' to turn off LED
            selectedPort1.writeBytes(new byte[]{'1'}, 1);
            System.out.println("Command sent to Arduino.");
        } else {
            System.out.println("Serial port not open.");
        }
    }

    static SerialPort sp;

    public void ArduinoKontrol() throws IOException, InterruptedException {

        if(HYIbaglanDurum == 1){

        selectedPort1.setComPortParameters(HYISP.getBaudRate(),HYISP.getNumDataBits(),HYISP.getNumStopBits(),HYISP.getParity());
            System.out.println("\nArduino Fonksiyonunun içine girildi ayarlar yapıldı!\n");
            System.out.println(ArduinoTest.getSelectionModel().getSelectedItem());
        Thread.sleep(500);

        if(ArduinoTest.getSelectionModel().getSelectedItem() ==  "Sadece Kırmızıyı Yak"){
            selectedPort1.getOutputStream().write(1);

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Kırmızı Işık Yakılıyor");
        } else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Sadece Sarıyı Yak") {
            selectedPort1.getOutputStream().write(2);

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Sarı ışık yakılıyor");
        }
        else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Sadece Maviyi Yak") {
            selectedPort1.getOutputStream().write(3);

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Mavi Işık Yanıyor");
        }else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Sırayla yanıp sönme") {
            selectedPort1.getOutputStream().write(4);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Sırayla Yanıp Sönme Oynatılıyor");

        }else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Strobe Efekti") {
            selectedPort1.getOutputStream().write(5);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Strobe Efekti Oynatılıyor");

        }
        else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Nefes Alma") {
            selectedPort1.getOutputStream().write(6);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Nefes Alma Oynatılıyor");

        }
        else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Sönme") {
            selectedPort1.getOutputStream().write(7);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n"+formattedDateTime+ " :: Arduinoda Sönme Oynatılıyor");

        }
        else if (ArduinoTest.getSelectionModel().getSelectedItem()==  "Test 1") {
            selectedPort1.getOutputStream().write(8);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);


            log.appendText("\n" + formattedDateTime + " :: Arduinoda Test 1 Oynatılıyor");
        }
        }else {
            System.out.println("Hyı Portu Kapalı");

        }

    }


    //program başlatıldığında otomatik çağırılır.

    Integer a = 5;

    int b = 5;
    public void roketUcur(){



        irtifaGraf.getData().add(new XYChart.Data(a.toString(),b));
        a++;
        b+=7 ;
        irtifaChart.getData().add(irtifaGraf);

    }

    public void initialize() {






        ArduinoTest.setItems(ArduinoListe);



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




        veriler.setAktarilacakverilerINIT();

    }



        public static void logAppend(String i){
                logtext = i;
                logAppend(logtext);

        }


    public void onHYIPortSelected() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String selectedPort = comboHYIComPort.getSelectionModel().getSelectedItem();
        System.out.println("Seçilen port: " + selectedPort);
        log.appendText("\n"+formattedDateTime+ " :: HYI Bağlantı Portu : " +selectedPort);
        HYISP.setPortName(selectedPort);

    }

    public void onHYIBoudRateSelected() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Integer selectedHYIBoudRate = comboHYIBoudRate.getSelectionModel().getSelectedItem();
        System.out.println("HYI İle kurulacak Boud Hızı " + selectedHYIBoudRate);
        log.appendText("\n"+formattedDateTime+ " :: HYI ile kurulacak Boud Hızı : " +selectedHYIBoudRate);
        HYISP.setBaudRate(selectedHYIBoudRate);

    }

    public void onHYIDataBitsSelected() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Integer selectedHYIDataBits = comboHYIDataBits.getSelectionModel().getSelectedItem();
        HYISP.setNumDataBits(selectedHYIDataBits);
        System.out.println("HYI veri aktaramında kıllanılaca data bit miktarı " + selectedHYIDataBits);
        log.appendText("\n"+formattedDateTime+ " :: HYI veri aktaramında kıllanılaca data bit miktarı: " +selectedHYIDataBits);

    }
    public void onHYIStopBitsSelected() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Integer selectedHYIStopBits = comboHYIStopBits.getSelectionModel().getSelectedItem();
        HYISP.setNumDataBits(selectedHYIStopBits);
        System.out.println("HYI veri aktaramında kıllanılaca stop bit miktarı " + selectedHYIStopBits);
        log.appendText("\n"+formattedDateTime+ " :: HYI veri aktaramında kıllanılaca stop bit miktarı: " +selectedHYIStopBits);

    }
    public void onHYIPairityBitsSelected() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Integer selectedHYIPairityBits = comboHYIPairityBits.getSelectionModel().getSelectedItem();
        HYISP.setParity(selectedHYIPairityBits);
        log.appendText("\n"+formattedDateTime+ " :: HYI veri aktaramında kıllanılacak pairity metodu : " +selectedHYIPairityBits);

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

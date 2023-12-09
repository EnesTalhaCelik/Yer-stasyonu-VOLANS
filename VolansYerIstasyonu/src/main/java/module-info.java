module com.volans.volansyeristasyonu {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fazecast.jSerialComm;

    opens com.volans.volansyeristasyonu to javafx.fxml;
    exports com.volans.volansyeristasyonu;
}
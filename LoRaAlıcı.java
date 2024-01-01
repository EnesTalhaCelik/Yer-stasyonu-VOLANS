package com.volans.volansyeristasyonu;
import com.fazecast.jSerialComm.SerialPort;

import java.io.OutputStream;

public class LoRaAlıcı {
    private SerialPort serialPort;

    public LoRaAlıcı(String portAdi) {
        serialPort = SerialPort.getCommPort(portAdi);
        serialPort.setBaudRate(9600);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(1);
        serialPort.setParity(SerialPort.NO_PARITY);


        //port açma
        if (serialPort.openPort()) {
            System.out.println("Port başarıyla açıldı.");
        } else {
            System.err.println("Port açılamadı.");
        }
    }

    // Modülü alıcı moduna ayarlar
    public void alıcıModunaGec() {
        if (serialPort.isOpen()) {
            KomutGönder("AT+MODE=RX\r\n");
            bekle(1000);
        } else {
            System.err.println("Port açık değil. Alıcı moduna geçilemiyor.");
        }
    }

    // Belirli bir süre bekler
    private void bekle(int milisaniye) {
        try {
            Thread.sleep(milisaniye);
        } catch (InterruptedException kesintiHatasi) { //bir thread uykudayken başka therad tarafından kesintiye uğraması hatası
            kesintiHatasi.printStackTrace();
        }
    }

    // porta komut gönderir
    private void KomutGönder(String komut) {
        try {
            OutputStream portCikisi = serialPort.getOutputStream();//seri porttan çıkış akışını alır.
            portCikisi.write(komut.getBytes());
            portCikisi.flush();
        } catch (Exception hata) {
            hata.printStackTrace();
        }
    }

    // Gelen verileri okur
    public void gelenVerileriOku() {
        if (serialPort.isOpen()) {
            byte[] gelenVeri = new byte[serialPort.bytesAvailable()]; //porttan gelen verileri okumk için dizi oluşturur.
            serialPort.readBytes(gelenVeri, gelenVeri.length);//gelen verileri okur.
            System.out.println("Gelen Veri: " + new String(gelenVeri)); //gelen verileri ekrana yazdırır.
        } else {
            System.err.println("Port açık değil. Veri okunamıyor.");
        }
    }

//portu kapatır
    public void kapat() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Port başarıyla kapatıldı.");
        }
    }

    public static void main(String[] args) {
        LoRaAlıcı loraAlıcı = new LoRaAlıcı(/*port adı*/);

        // Alıcı moduna geç
        loraAlıcı.alıcıModunaGec();

        // Gelen verileri oku
        loraAlıcı.gelenVerileriOku();

        // İşlem bittikten sonra seri portu kapat
        loraAlıcı.kapat();
    }

}

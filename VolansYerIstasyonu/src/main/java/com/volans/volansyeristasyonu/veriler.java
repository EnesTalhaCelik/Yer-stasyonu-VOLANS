package com.volans.volansyeristasyonu;

import java.nio.ByteBuffer;

public class veriler {



// Buradaki değişkenler teknofest yarışmasında hakem yer istasyonuna göndermemiz gereken verilerdir.
    private static float jiroskopX;
    private static float jiroskopY;
    private static float jiroskopZ;
    private static float ivmeX;
    private static float ivmeY;
    private static float ivmeZ=9.77779f;
    private static float aci = 98.85f;



    private static float gorevYukuBoylam;
    private static float gorevYukuEnlem;
    private static float gorevYukuGpsIrtifa;
    private static float roketBoylam;
    private static float roketEnlem;
    private static float roketGpsIrtifa; //gps verisinden gelen irtifa değeridir.
    private static float basincIrtifa; //basınç sensöründen gelen irtifa değeridir

    private static float kademeBoylam ;

    private static float kademeEnlem;

    private static float kademeGpsIrtifa;

    private static int durum ;

    private static int crc;

    private static int takimID;

    private static int paketSayac;
//----------------------------------------------

// Seri haberleşme
    static seriHaberleşme.SerialPortConfiguration LoraSP = new seriHaberleşme.SerialPortConfiguration();
    static seriHaberleşme.SerialPortConfiguration HYISP = new seriHaberleşme.SerialPortConfiguration();


    private static byte[] Aktarilacakveriler = new byte[78]; //Hakem bilgisayarına göndereceğimiz veriler


    public static float getJiroskopX() {
        return jiroskopX;
    }

    public static void setJiroskopX(float jiroskopX) {
        veriler.jiroskopX = jiroskopX;
    }

    public static float getJiroskopY() {
        return jiroskopY;
    }

    public static void setJiroskopY(float jiroskopY) {
        veriler.jiroskopY = jiroskopY;
    }

    public static float getJiroskopZ() {
        return jiroskopZ;
    }

    public static void setJiroskopZ(float jiroskopZ) {
        veriler.jiroskopZ = jiroskopZ;
    }

    public static float getIvmeX() {
        return ivmeX;
    }

    public static void setIvmeX(float ivmeX) {
        veriler.ivmeX = ivmeX;
    }

    public static float getIvmeY() {
        return ivmeY;
    }

    public static void setIvmeY(float ivmeY) {
        veriler.ivmeY = ivmeY;
    }

    public static float getIvmeZ() {
        return ivmeZ;
    }

    public static void setIvmeZ(float ivmeZ) {
        veriler.ivmeZ = ivmeZ;
    }

    public static float getAci() {
        return aci;
    }

    public static void setAci(float aci) {
        veriler.aci = aci;
    }

    public static float getGorevYukuBoylam() {
        return gorevYukuBoylam;
    }

    public static void setGorevYukuBoylam(float gorevYukuBoylam) {
        veriler.gorevYukuBoylam = gorevYukuBoylam;
    }

    public static float getGorevYukuEnlem() {
        return gorevYukuEnlem;
    }

    public static void setGorevYukuEnlem(float gorevYukuEnlem) {
        veriler.gorevYukuEnlem = gorevYukuEnlem;
    }

    public static float getGorevYukuGpsIrtifa() {
        return gorevYukuGpsIrtifa;
    }

    public static void setGorevYukuGpsIrtifa(float gorevYukuGpsIrtifa) {
        veriler.gorevYukuGpsIrtifa = gorevYukuGpsIrtifa;
    }

    public static float getRoketBoylam() {
        return roketBoylam;
    }

    public static void setRoketBoylam(float roketBoylam) {
        veriler.roketBoylam = roketBoylam;
    }

    public static float getRoketEnlem() {
        return roketEnlem;
    }

    public static void setRoketEnlem(float roketEnlem) {
        veriler.roketEnlem = roketEnlem;
    }

    public static float getRoketGpsIrtifa() {
        return roketGpsIrtifa;
    }

    public static void setRoketGpsIrtifa(float roketGpsIrtifa) {
        veriler.roketGpsIrtifa = roketGpsIrtifa;
    }

    public static float getKademeBoylam() {
        return kademeBoylam;
    }

    public static void setKademeBoylam(float kademeBoylam) {
        veriler.kademeBoylam = kademeBoylam;
    }

    public static float getKademeEnlem() {
        return kademeEnlem;
    }

    public static void setKademeEnlem(float kademeEnlem) {
        veriler.kademeEnlem = kademeEnlem;
    }

    public static float getKademeGpsIrtifa() {
        return kademeGpsIrtifa;
    }

    public static void setKademeGpsIrtifa(float kademeGpsIrtifa) {
        veriler.kademeGpsIrtifa = kademeGpsIrtifa;
    }

    public static int getDurum() {
        return durum;
    }

    public static void setDurum(int durum) {
        veriler.durum = durum;
    }

    public static byte[] getAktarilacakveriler() {
        return Aktarilacakveriler;
    }

    public static void setAktarilacakveriler(byte aktarilacakveriler,int sira) {
        Aktarilacakveriler[sira] = aktarilacakveriler;
    }



    //HYI istasyonuna aktaracağımız verilerin sabit bölümlerini ekliyoruz
    public static void setAktarilacakverilerINIT() {
        Aktarilacakveriler[0] = (byte) 255;
        Aktarilacakveriler[1] = (byte) 255;
        Aktarilacakveriler[2] = (byte) 84;
        Aktarilacakveriler[3] = (byte) 82;
        Aktarilacakveriler[76] = (byte) 13;
        Aktarilacakveriler[77] = (byte) 10;
    }

    public static float getBasincIrtifa() {
        return basincIrtifa;
    }

    public static void setBasincIrtifa(float basincIrtifa) {
        veriler.basincIrtifa = basincIrtifa;
    }



    public static void arrayFloatToByte(){
        int sayac = 4; //yazmak istediğimiz veriler arrayin 6.hanesine takbul ediyor.O noktadan itibaren yazmaya başlıyoruz.

        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        ByteBuffer bufferINT = ByteBuffer.allocate(Integer.BYTES);

        bufferINT.putInt(takimID);
        System.arraycopy(bufferINT.array(), 0, Aktarilacakveriler, sayac, 1);
        bufferINT.clear();
        sayac += 1;
        bufferINT.putInt(paketSayac);
        System.arraycopy(bufferINT.array(), 0, Aktarilacakveriler, sayac, 1);
        bufferINT.clear();
        sayac += 1;
        buffer.putFloat(basincIrtifa);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(roketGpsIrtifa);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(roketEnlem);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(roketBoylam);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(gorevYukuGpsIrtifa);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(gorevYukuEnlem);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(gorevYukuBoylam);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();

        // !! bu noktada kademe gps irtifa vs verilerini geçiyoruz bunlar zorlu görev kategorisinde olan değerler!!
        // Buralara sabit değerler koyacağız.
        sayac+=12;


        buffer.putFloat(jiroskopX);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(jiroskopY);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(jiroskopZ);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(ivmeX);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(ivmeY);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(ivmeZ);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        buffer.putFloat(aci);
        System.arraycopy(buffer.array(), 0, Aktarilacakveriler, sayac, 4);
        sayac += 4;
        buffer.clear();
        bufferINT.putInt(durum);
        System.arraycopy(bufferINT.array(), 0, Aktarilacakveriler, sayac, 1);
        bufferINT.clear();
        sayac += 1;
        bufferINT.putInt(crc);
        System.arraycopy(bufferINT.array(), 0, Aktarilacakveriler, sayac, 1);
        bufferINT.clear();
        sayac += 1;
    }




}

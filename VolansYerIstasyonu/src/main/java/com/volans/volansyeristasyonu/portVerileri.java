package com.volans.volansyeristasyonu;

public class portVerileri {
    private static String ComPort;
    private static int BoudRate;
    private static int DataBits;
    private static int StopBits;
    private static int Pairity;

    public static String getComPort() {
        return ComPort;
    }

    public static void setComPort(String comPort) {
        ComPort = comPort;
    }

    public static int getBoudRate() {
        return BoudRate;
    }

    public static void setBoudRate(int boudRate) {
        BoudRate = boudRate;
    }

    public static int getDataBits() {
        return DataBits;
    }

    public static void setDataBits(int dataBits) {
        DataBits = dataBits;
    }

    public static int getStopBits() {
        return StopBits;
    }

    public static void setStopBits(int stopBits) {
        StopBits = stopBits;
    }

    public static int getPairity() {
        return Pairity;
    }

    public static void setPairity(int pairity) {
        Pairity = pairity;
    }
}

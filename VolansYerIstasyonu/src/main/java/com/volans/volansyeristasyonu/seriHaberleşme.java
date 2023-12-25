package com.volans.volansyeristasyonu;
import com.fazecast.jSerialComm.SerialPort;




public class seriHaberleşme {
    //seri haberleşme test dosyasıdır
    static String portName = "COM12"; //test için bu verileri girdim

    public static void main(String[] args) {


        // Set the serial port configuration
        SerialPortConfiguration config = new SerialPortConfiguration();
        config.setBaudRate(9600);
        config.setNumDataBits(8);
        config.setNumStopBits(1);
        config.setParity(SerialPort.NO_PARITY);

        // Call the method to perform serial writing with the specified configuration
        writeByteToSerialPort(portName, (byte) 65, config);
    }

    public static void writeByteToSerialPort(String portName, byte byteValue, SerialPortConfiguration config) {
        // Get the desired serial port
        SerialPort serialPort = SerialPort.getCommPort(portName);

        // Set the parameters for the serial port
        serialPort.setBaudRate(config.getBaudRate());
        serialPort.setNumDataBits(config.getNumDataBits());
        serialPort.setNumStopBits(config.getNumStopBits());
        serialPort.setParity(config.getParity());

        // Open the serial port and perform writing (error handling remains the same)
        try {
            if (serialPort.openPort()) {
                try (java.io.OutputStream outputStream = serialPort.getOutputStream()) {
                    outputStream.write(byteValue);
                    System.out.println("Byte value " + byteValue + " written to " + portName);
                }
            } else {
                System.err.println("Error: Could not open serial port " + portName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the serial port
            serialPort.closePort();
        }
    }

    // Additional class to hold serial port configuration
    static class SerialPortConfiguration {

        private String portName;
        private int baudRate;
        private int numDataBits;
        private int numStopBits;
        private int parity;


        public String getPortName() {
            return portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
        }

        public void setBaudRate(int baudRate) {
            this.baudRate = baudRate;
        }

        public void setNumDataBits(int numDataBits) {
            this.numDataBits = numDataBits;
        }

        public void setNumStopBits(int numStopBits) {
            this.numStopBits = numStopBits;
        }

        public void setParity(int parity) {
            this.parity = parity;
        }

        public int getBaudRate() {
            return baudRate;
        }

        public int getNumDataBits() {
            return numDataBits;
        }

        public int getNumStopBits() {
            return numStopBits;
        }

        public int getParity() {
            return parity;
        }

    }
}




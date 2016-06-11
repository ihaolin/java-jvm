package me.hao0.jvm.gc;

import java.util.concurrent.locks.LockSupport;

public class Boxing {

    private static volatile Double sensorValue;

    private static void readSensor() {
        while(true) {
            sensorValue = Math.random();
        }
    }

    private static void processSensorValue(Double value) {
        if(value != null) {
            // Be warned: may take more than one usec on some machines, especially Windows
            LockSupport.parkNanos(1000);
        }
    }

    private static void initSensor() {

        Thread sensorReader = new Thread(new Runnable() {
            @Override
            public void run() {
                Boxing.readSensor();
            }
        });

        sensorReader.setDaemon(true);
        sensorReader.start();
    }

    public static void main(String[] args) {
        int iterations = args.length > 0 ? Integer.parseInt(args[0]) : 1_000_000;
        initSensor();

        for(int i = 0; i < iterations; i ++) {
            processSensorValue(sensorValue);
        }
    }
}
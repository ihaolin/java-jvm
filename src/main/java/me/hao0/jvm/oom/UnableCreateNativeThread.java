package me.hao0.jvm.oom;

import java.lang.management.ManagementFactory;

/**
 * @author haolin
 * @date 6/18/16
 * @mailto haolin.h0@gmail.com
 */
public class UnableCreateNativeThread {

    private static volatile int threadCount = 0;

    public static void main(String[] args) {

        System.err.println(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);

        while (true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println(++threadCount);
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
    }
}

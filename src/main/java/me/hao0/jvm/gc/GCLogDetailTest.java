package me.hao0.jvm.gc;

import java.util.HashSet;
import java.util.Set;

/**
 * GC日志分析
 * -Xms20M -Xmx20M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */
public class GCLogDetailTest {

    // 1M
    private static int M_1 = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        Set<byte[]> lives = new HashSet<>();
        byte[] dead = null;

        dead = new byte[M_1 * 2];
        System.out.println("dead allocated 2M");

        dead = new byte[M_1 * 3];
        System.out.println("dead allocated 3M");

        lives.add(new byte[M_1 * 2]);
        System.out.println("lives allocated 2M");
        lives.add(new byte[M_1 * 2]);
        System.out.println("lives allocated 2M");
        lives.add(new byte[M_1 * 2]);
        System.out.println("lives allocated 2M");
        lives.add(new byte[M_1 * 2]);
        System.out.println("lives allocated 2M");
        lives.add(new byte[M_1 * 2]);
        System.out.println("lives allocated 2M");

        dead = new byte[M_1 * 1];
        System.out.println("dead allocated 1M");
//        dead = new byte[M_1 * 1];
//        System.out.println("dead allocated 1M");
//        dead = new byte[M_1 * 1];
//        System.out.println("dead allocated 1M");
//        dead = new byte[M_1 * 1];
//        System.out.println("dead allocated 1M");

        Thread.sleep(1000000L);
    }
}
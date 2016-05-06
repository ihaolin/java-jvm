package me.hao0.jvm.gc;

import java.util.HashSet;
import java.util.Set;

/**
 * 分别使用SerialGC和ParallelGC性能对比
 * -Xms1g -Xmx1g -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 * -Xms1g -Xmx1g -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */
public class SerialGCVSParallelGCTest {

    // 1M
    private static int M_1 = 1024 * 1024;

    public static void main(String[] args) {

        // 保存存活对象
        Set<byte[]> lives = new HashSet<>();
        byte[] dead = null;

        // 分配 0.001M * 100
        for (int i=0; i<100 * 1000; i++){
            if (i % 3 == 0){
                lives.add(new byte[M_1 / 1000]);
            } else {
                dead = new byte[M_1 / 1000];
            }
        }
        System.out.println("allocated 100m");

        // 分配 0.001M * 100
        for (int i=0; i<100 * 1000; i++){
            if (i % 3 == 0){
                lives.add(new byte[M_1 / 1000]);
            } else {
                dead = new byte[M_1 / 1000];
            }
        }
        System.out.println("allocated 100m");

        // 分配 2M * 50
        for (int i=0; i<50; i++){
            if (i % 3 == 0){
                lives.add(new byte[M_1 * 2]);
            } else {
                dead = new byte[M_1 * 2];
            }
        }
        System.out.println("allocated 100m");
    }
}
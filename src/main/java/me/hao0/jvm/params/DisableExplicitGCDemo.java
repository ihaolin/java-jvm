package me.hao0.jvm.params;

import java.nio.ByteBuffer;

/**
 * java -XX:MaxDirectMemorySize=10m -XX:+PrintGC -XX:+DisableExplicitGC
 * +DisableExplicitGC导致allocateDirect申请的内存不能被gc，导致java.lang.OutOfMemoryError: Direct buffer memory
 * <p>
 *  由于+DisableExplicitGC禁止了System.gc()，导致ByteBuffer.allocateDirect申请的内存不能被gc
 * </p>
 */
public class DisableExplicitGCDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            ByteBuffer.allocateDirect(128);
        }
        System.out.println("Done");
    }

}  
package me.hao0.jvm.oom;

import javassist.ClassPool;

public class MicroGenerator {

    public static void main(String[] args) throws Exception {

        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println(e.getMessage());
            }
        });

        for (int i = 0; i < 100_000_000; i++) {
            generate("eu.plumbr.Generated" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}
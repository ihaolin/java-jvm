package me.hao0.jvm.oom;

public class Metaspace {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
        }
    }
}
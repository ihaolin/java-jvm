package me.hao0.jvm.deadlock;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader2 extends URLClassLoader{
    static ClassLoader loader;
    MyClassLoader2(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        loader = this;
    }
    public static ClassLoader getClassLoader() {
        return loader;
    }
}
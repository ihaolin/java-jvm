package me.hao0.jvm.classloader.identify;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassIdentifyTest {

    public static void main(String[] args) throws Exception {
        File path = new File("myclasses");

        System.out.println(path.toURI().toURL());

        URL[] urls = new URL[]{path.toURI().toURL()};

        URLClassLoader cl1 = new URLClassLoader(urls);
        URLClassLoader cl2 = new URLClassLoader(urls);

        Class c1 = cl1.loadClass("Foo");
        Class c2 = cl2.loadClass("Foo");
        System.out.println("same class instance: " + (c1 == c2));

        Object o1 = c1.newInstance();
        Object o2 = c2.newInstance();

        Method m = c1.getDeclaredMethod("isFoo", Object.class);
        m.invoke(o1, o2);
    }
}
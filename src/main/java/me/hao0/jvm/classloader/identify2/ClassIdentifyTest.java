package me.hao0.jvm.classloader.identify2;

import me.hao0.jvm.classloader.FileSystemClassLoader;
import java.lang.reflect.Method;

/**
 * Created by haolin on 4/5/16.
 */
public class ClassIdentifyTest {

    public static void main(String[] args){
        String classDataRootPath = "/Users/haolin/Github/jvm/myclasses";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);

            System.out.println(obj1.getClass().getClassLoader());
            System.out.println(obj2.getClass().getClassLoader());
            System.out.println(obj1.getClass() == obj2.getClass());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

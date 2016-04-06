package me.hao0.jvm.classloader;

/**
 * Created by haolin on 4/5/16.
 */
public class ThreadContextClassLoader {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

}

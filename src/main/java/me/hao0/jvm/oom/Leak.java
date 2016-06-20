package me.hao0.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class Leak {

    static List list = new ArrayList();

    public static void main(String[] args) {
        for (int i = 0; i >= 0; i++) {
            list.add(i);
        }
        System.out.println("I will either reach here or die trying");
    }

}
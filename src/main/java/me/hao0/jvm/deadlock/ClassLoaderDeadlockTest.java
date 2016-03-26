package me.hao0.jvm.deadlock;

import java.net.URL;

public class ClassLoaderDeadlockTest {
	MyClassLoader1 mycl1;
	MyClassLoader2 mycl2;

	public static void main(String[] args) {
		new ClassLoaderDeadlockTest().test();
	}

	public void test() {
		try {
			mycl1 = new MyClassLoader1(new URL[] { new URL(
					"file:///Users/haolin/Learning/java/codes/core/classloader-deadlock/bin/") });
			mycl2 = new MyClassLoader2(new URL[] { new URL(
					"file:///Users/haolin/Learning/java/codes/core/classloader-deadlock/bin/") }, mycl1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("About to load class A with mycl1");
					mycl1.loadClass("package1.A");
					System.out.println("Loaded Class A with mycl1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		try {
			System.out.println("About to load class C with mycl2");
			mycl2.loadClass("package2.C");
			System.out.println("Loaded Class C with mycl2");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
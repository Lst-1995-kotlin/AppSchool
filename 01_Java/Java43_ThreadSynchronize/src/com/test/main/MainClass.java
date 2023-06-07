package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		Thread3 t3 = new Thread3();
		
		t1.start();
		t2.start();
		t3.start();
		
		
	}

}

class Thread1 extends Thread {
	@Override
    public void run() {
        TestClass.testMethod("Thread1");
    }
}

class Thread2 extends Thread {
	@Override
    public void run() {
        TestClass.testMethod("Thread2");
    }
}

class Thread3 extends Thread {
	@Override
    public void run() {
        TestClass.testMethod("Thread3");
    }
}

class TestClass{
	public synchronized static void testMethod(String name) {
		for(int i = 0; i < 20; i++) {
			System.out.printf("%s : %d\n", name,i);
		}
	}
}


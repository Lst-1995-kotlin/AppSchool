package com.study.com;

public class StudtClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Runnable 인터페이스를 구현한 클래스
		TestClass3 t3 = new TestClass3();
		Thread thread = new Thread(t3);
		thread.start();
		
		// Runnable 인터페이스를 구현한 예외를 발생시키는 클래스
		TestClass4 t4 = new TestClass4();
		Thread thread4 = new Thread(t4);
		thread4.start();
	}

}

// Runnable 인터페이스를 구현한 클래스
class TestClass3 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i < 10; i++) {
				Thread.sleep(300);
				System.out.println("TestClass3 interface");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

//Runnable 인터페이스를 구현한 예외를 발생하는 클래스
class TestClass4 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i < 10; i++) {
				Thread.sleep(300);
				if(i > 5)
					throw new Exception();
				
				System.out.println("TestClass4 interface");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
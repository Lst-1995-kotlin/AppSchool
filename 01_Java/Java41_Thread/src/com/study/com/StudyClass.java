package com.study.com;

public class StudyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Thread를 상속받은 클래스를 이용한 객체 생성
		TestClass1 t1 = new TestClass1();
		// start()을 이용하여 해당 쓰레드 실행
		t1.start();
		// Thread를 상속받은 클래스를 이용한 객체 생성
		TestClass2 t2 = new TestClass2();
		// start()을 이용하여 해당 쓰레드 실행
		t2.start();
	}

}

//Thread를 상속받은 클래스
class TestClass1 extends Thread{
	// 쓰래드를 발생시켜 처리할 메서드
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				Thread.sleep(500);
				System.out.println("TestClass1");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//Thread를 상속받은 클래스
class TestClass2 extends Thread{
	// 쓰래드를 발생시켜 처리할 메서드
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				Thread.sleep(500);
				if(i > 5) {
					throw new Exception();
				}
				System.out.println("TestClass2");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
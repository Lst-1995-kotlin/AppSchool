package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 쓰레드 우선순위.
		// 여러 쓰레드를 가동 시켰을 대 우선적으로 처리될 쓰레드를 지정할 수 있다.
		
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		Thread3 t3 = new Thread3();
		
		// 쓰레드 우선 순위 확인
		int p1 = t1.getPriority();
		int p2 = t2.getPriority();
		int p3 = t3.getPriority();
		System.out.printf("변경 전 우선 순위 p1 : %d\n", p1);
		System.out.printf("변경 전 우선 순위 p2 : %d\n", p2);
		System.out.printf("변경 전 우선 순위 p3 : %d\n", p3);
		
		// 우선순위 설정
		t1.setPriority(10);
		t2.setPriority(1);
		t3.setPriority(1);
		
		int p4 = t1.getPriority();
		int p5 = t2.getPriority();
		int p6 = t3.getPriority();
		
		System.out.printf("변경 후 우선 순위 p4 : %d\n", p4);
		System.out.printf("변경 후 우선 순위 p5 : %d\n", p5);
		System.out.printf("변경 후 우선 순위 p6 : %d\n", p6);
		
		// 쓰레드 가동
		t1.start();
		t2.start();
		t3.start();
		
	}

}

class Thread1 extends Thread{
    @Override
    public void run() {
        
        for(int i = 0 ; i < 20 ; i++) {
            System.out.println("Thread1");
        }
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        
        for(int i = 0 ; i < 20 ; i++) {

            System.out.println("Thread2");
        }
       
    }
}

class Thread3 extends Thread{
    @Override
    public void run() {
    	
        for(int i = 0 ; i < 20 ; i++) {

            System.out.println("Thread3");
        }
    
    }
}
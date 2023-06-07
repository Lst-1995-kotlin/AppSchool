package com.studty.lst;


public class Study3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		// 인터페이스에 정의한 변수 사용
		System.out.printf("TestInterface1.a : %d\n", TestInterface1.a);
		// 인터페이스에 정의한 메서드 사용
		TestInterface1.interfaceM2();
		// 인터페이스를 구현한 클래스를 사용하여 객체 생성
		TestClass1 t1 = new TestClass1();
		TestClass2 t2 = new TestClass2();
		// 인터페이스를 구현한 클래스를 사용하여 생성한 객체를 이용하여
		// 오버라이딩 한 메서드 사용
		t1.interfaceM1();
		
		t2.interfaceM1();
		t2.interfaceM3();
		
		// 클래스가 구현한 인터페이스 타입형 변수에 담을 수 있다.
		TestInterface1 t10 = new TestClass1();
		t10.interfaceM1();
		
		TestInterface2 t20 = new TestClass2();
		t20.interfaceM3();

	}

}

interface TestInterface1{
	// 인터페이스에 정의한 변수는 static final 변수이다.
	int a = 100;
	
	// 인터페이스에 정의한 메서드는 기본적으로 추상 메서드 이다.
	public void interfaceM1();
	
	// 인터페이스에 메서드를 정의할 대 static 메서드를 사용할 수 있다.
	public static void interfaceM2() {
		System.out.println("TestInterface1 내 static를 이용하여 생성한 메서드");
	}
	
}

interface TestInterface2{
	// 인터페이스에 정의한 변수는 static final 변수이다.
	int b = 100;
	
	// 인터페이스에 정의한 메서드는 기본적으로 추상 메서드 이다.
	public void interfaceM3();
	
	// 인터페이스에 메서드를 정의할 대 static 메서드를 사용할 수 있다.
	public static void interfaceM4() {
		System.out.println("TestInterface2 내 static를 이용하여 생성한 메서드");
	}
	
}

// 인터페이스를 구현한 클래스
// 클래스 내 오버라이딩을 하지 않으면 에러가 발생한다.
class TestClass1 implements TestInterface1{
	
	@Override
	public void interfaceM1() {
		// TODO Auto-generated method stub
		System.out.println("TestInterface1를 구현한 클래스1");
	}
	
}

//인터페이스를 다수 구현한 클래스
class TestClass2 implements TestInterface1, TestInterface2{
	
	@Override
	public void interfaceM1() {
		// TODO Auto-generated method stub
		System.out.println("TestInterface1를 구현한 클래스2");
	}

	@Override
	public void interfaceM3() {
		// TODO Auto-generated method stub
		System.out.println("TestInterface2를 구현한 클래스2");
	}
	
}


// 인터페이스의 인터페이스 상속
// 자주 구현해야하는 인터페이스들이 많을 경우
// 하나의 인터페이스에 모두 상속 시키고
// 상속 받은 인터페이스 하나만 클래스에 구현하면 편하게 사용 할 수 있다.

interface Inter1{
	public void method1();
}

interface Inter2{
	public void method2();
}

interface Inter3{
	public void method3();
}

// 인터페이스 Inter1~3 까지 상속 받은 인터페이스
interface Inter4 extends Inter1, Inter2, Inter3{
	public void method4();
}

// 여러 인터페이스를 상속 받은 인터페이스를 구현하는 클래스 생성
// 여러 인터페이스에 있는 추상 메서드를 오버라이딩 해야함.
class TestClass3 implements Inter4{

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method4() {
		// TODO Auto-generated method stub
		
	}
	
}

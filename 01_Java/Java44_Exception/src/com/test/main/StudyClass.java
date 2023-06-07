package com.test.main;

public class StudyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testMethod3는 예외들을 throws하고 있기 때문에
		// 반드시 예외처리를 해줘야 한다.
		try {
			ExcepClass t200 = new ExcepClass();
			t200.testM3(-100);
			t200.testM3(200);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}


class ExcepClass{
	// 기본 메서드
	public void testM1() {
		System.out.println("ExcepClass 내 기본 메서드");
	}
	// 조건에 안맞으면 예외상황 발생 하는 메서드
	public void testM2(int a) {
		if(a > 10) {
			// 
			throw new ArithmeticException("여기에 문자열 넣으면?");
		}
		System.out.printf("a : %d\n",a);
	}
	
	public void testM3(int a) throws Exception, ArithmeticException {
		// a1에 전달된 값이 양수가 아니면 오류를 발생시킨다.
		if(a <= 0) {
			throw new ArithmeticException("양수만 넣어라...");
		}
		System.out.println("a1은 양수입니다");
	}

}


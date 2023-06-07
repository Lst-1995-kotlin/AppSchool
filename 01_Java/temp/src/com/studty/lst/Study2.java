package com.studty.lst;

public class Study2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 자식 클래스를 이용하여 객체를 생성.
		ChildClass c1 = new ChildClass();
		c1.printInfo();
		
//		 자식 클래스에서 메서드를 오버라이딩하였을 때
//		 만약 자식 클래스를 이용하여 만든 객체에서 부모 클래스에서 정의한 메소드를
//		 호출하고 싶다면? -> 자식 클래스 내 부모 클래스에 있는 변수나 메서드를 사용하기 위한
		// 별도의 메서드를 생성하지 않는 이상 사용하지 못한다.
		//ChildClass c2 = new ChildClass();
		
		
		
	}

}

// 부모 클래스를 생성한다.
class ParentsClass {
	
	String me;
	
	public void ParentsClass(){
		String me = "부모클래스";
	}
	
	
	public void printInfo() {
		System.out.print("부모 클래스에서 생성된 메서드\n");
		System.out.printf("me : %s\n",me);
	}
}

// extends를 사용하여 부모클래스에서 상속을 받는 클래스.

class ChildClass extends ParentsClass{
	
	// 자식 클래스에서 부모클래스에 있는 메서드와 상수를 수정한다.
	// -> 오버라이딩
	
	// 변수 변경
	String me = "자식클래스";
	// 메서드 변경
	public void printInfo() {
		System.out.print("자식 클래스에서 생성된 메서드\n");
		System.out.printf("me : %s\n",me);
		System.out.println();
		
		System.out.print("자식 클래스에서 부모 클래스에 선언된 메서드 호출~! \n");
		super.printInfo();
		System.out.println();
		
		System.out.print("자식 클래스에서 부모 클래스에 선언된 변수 호출~! \n");
		System.out.printf("me : %s\n",super.me);
	}
}
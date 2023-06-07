package com.basicStudy.com;

public class SmartPhoneFactory {

	String name;
	Integer ram;
	Integer storage;
	
	// 생성 될 때 같이 전달받은 타입에 따라 생성하는 스마트폰 정보가 다르다.
	// 생성자 : 클래스를 생성하게 되면 자동으로 호출 되는 메서드
	public SmartPhoneFactory(int type) {
		if(type == 1) {
			this.name = "아이폰";
			this.ram = 16;
			this.storage = 128;
		} else if(type == 2) { // 입력 받은 타입이 2일 때
			this.name = "아이폰";
			this.ram = 8;
			this.storage = 256;
		} else if(type == 3) { // 입력 받은 타입이 3일 때
			this.name = "갤럭시";
			this.ram = 12;
			this.storage = 1024;
		} else { // 입력 받은 타입이 4일 때
			this.name = "갤럭시";
			this.ram = 24;
			this.storage = 512;
		}
	}
	
	// 스마트폰의 정보를 출력하는 메서드
	protected void printSmartInfo() {
		System.out.printf("이름 : %s\n",name);
		System.out.printf("램 : %d 기가\n",ram);
		System.out.printf("저장소 : %s 기가\n",storage);
		System.out.println("--------------");
	}
	
	
}

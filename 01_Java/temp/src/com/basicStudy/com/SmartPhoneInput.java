package com.basicStudy.com;

import java.util.ArrayList;
import java.util.Scanner;

public class SmartPhoneInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 입력받은 타입을 저장 할 ArrayList를 생성한다.
		ArrayList<SmartPhoneFactory> phoneArray = new ArrayList<SmartPhoneFactory>();
		
		// 사용자가 입력한 숫자를 저장한다.
		Scanner scan = new Scanner(System.in);
		System.out.print("생성할 스마트폰 타입을 지정해주십시오.");
		int type = scan.nextInt();
		// 하기 내용을 반복한다. 0 이 나오기 전까지.
		do{
			// 스마트폰은 SmartPhoneFactory Class를 이용하여 생성한다.
			// 사용자에게 타입을 입력을 받는다. (0~4)
			// 0 이라면 지금까지 입력 받은 타입의 스마트폰 정보 전체를 출력하고
			// 반복문을 탈출한다.
			if(type == 0) 
				break;
			else {
				// 1 이라면 타입1) 이름 : 아이폰, 램 : 16기가, 저장소 : 128기가
				// 2 이라면 타입1) 이름 : 아이폰, 램 : 8기가, 저장소 256기가
				// 3 이라면 타입1) 이름 : 갤럭시, 램 : 12기가, 저장소 1024기가
				// 4 이라면 타입4) 이름 : 갤럭시, 램 : 24기가, 저장속 512기가
				phoneArray.add(new SmartPhoneFactory(type));
			}
			System.out.print("생성할 스마트폰 타입을 지정해주십시오.");
			type = scan.nextInt();
		} while(type != 0);
		
		if(phoneArray.isEmpty()) 
			System.out.print("생성된 스마트폰이 없습니다.");
		else {
			System.out.println("생성된 스마트폰 목록 리스트");
			System.out.println("--------------");
			for(SmartPhoneFactory phone : phoneArray) {
				phone.printSmartInfo();
			}
		}
	}

}

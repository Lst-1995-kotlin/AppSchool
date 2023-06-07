package com.test.zoo.study;

import java.util.Scanner;

//예시
//동물의 종류를 입력해주세요
//1. 코끼리, 2. 사막여우, 3. 캥거루, 0. 입력끝 : 1
//동물의 이름을 입력해주세요 : 맘모스
//위의 입력을 0을 입력할 때 까지 반복한다....
//0을 눌러 입력이 끝나면 모든 동물의 정보를 출력한다.
//타입 : 코끼리
//이름 : 맘모스
//다리 : 4개
//코 : 길다
//몸 : 크다
//식사방법 : 손을 이용해 먹는다.
//.......

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 동물원
		// 모든 클래스는 com.test.zoo 라는 패키지에 만들어 준다
		// 동물들의 특징은 다음과 같다.
		// 코끼리
		// 다리 : 4개, 코 : 길다, 몸 : 크다, 식사방법 : 코를 이용해 먹는다
		// 사막여우
		// 다리 : 4개, 코 : 짧다, 몸 : 작다, 식사방법 : 손을 이용해 먹는다
		// 캥거루
		// 다리 : 2개, 코 : 짧다, 몸 : 크다, 식사방법 : 나뭇잎을 뜯어 먹는다.
		// 다리, 코, 몸, 식사방법은 변수로 정의한다.
		// 각 동물은 자신의 정보를 출력하는 메서드를 가지고 있다.
		
		// 예시
		// 동물의 종류를 입력해주세요
		// 1. 코끼리, 2. 사막여우, 3. 캥거루, 0. 입력끝 : 1
		// 동물의 이름을 입력해주세요 : 맘모스
		// 위의 입력을 0을 입력할 때 까지 반복한다....
		// 0을 눌러 입력이 끝나면 모든 동물의 정보를 출력한다.
		// 타입 : 코끼리
		// 이름 : 맘모스
		// 다리 : 4개
		// 코 : 길다
		// 몸 : 크다
		// 식사방법 : 손을 이용해 먹는다.
		// .............
		
		Scanner scan = new Scanner(System.in);
		// 객체를 저장할 배열 임시크기 100 
		Zoo [] animal = new Zoo[100];
		
		// 하기 내용을 반복한다.
		
		for(int i = 0; i < 100; i++) {
			// 질문 출력
			System.out.println("동물의 종류를 입력해주세요. ");
			System.out.println("1. 코끼리, 2. 사막여우, 3. 캥거루, 0. 입력끝 ");
			// 종류 입력 받음
			int inputkind = scan.nextInt();
			if(inputkind == 0)
				break;
			else {
				System.out.println("동물의 이름을 입력해주세요 ");
				// 이름 입력받음
				String inputname = scan.next();
				if(inputkind == 1) {
					animal[i] = new Elephant();
					animal[i].name = inputname;
				} else if(inputkind == 2) {
					animal[i] = new DesertFox();
					animal[i].name = inputname;
				} else { // 3
					animal[i] = new Kangaroo();
					animal[i].name = inputname;
				}
			}
		}
		
		for(int i = 0; i < animal.length; i++) {
			if(animal[i] != null) {
				animal[i].printInfo();
				System.out.println();
			}
			else break;
		}
		
		scan.close();
	}
}

class Zoo {
	String kind; // 종류
	String name; // 이름
	int leg; // 다리개수
	String nose; // 코
	String bodySize; // 몸크기
	String howEat; // 식사 방법

	//출력을 담당하는 메서드 -> 상속예정
	public void printInfo(){
		System.out.printf("타입 : %s\n", kind);
		System.out.printf("이름 : %s\n", name);
		System.out.printf("다리 : %d\n", leg);
		System.out.printf("코 : %s\n", nose);
		System.out.printf("몸 : %s\n", bodySize);
		System.out.printf("식사방법 : %s\n", howEat);
	}
}

class Elephant extends Zoo{
	public Elephant() {
		this.kind = "코끼리"; // 종류
		this.name = "이름없음"; // 이름
		this.leg = 4; // 다리개수
		this.nose = "길다"; // 코
		this.bodySize = "크다"; // 몸크기
		this.howEat = "코를 이용해 먹는다.";// 식사 방법
	}
}

class DesertFox extends Zoo{
	public DesertFox() {
		this.kind = "사막여우"; // 종류
		this.name = "이름없음"; // 이름
		this.leg = 4; // 다리개수
		this.nose = "짧다"; // 코
		this.bodySize = "작다"; // 몸크기
		this.howEat = "손을 이용해 먹는다.";// 식사 방법
	}
}

class Kangaroo extends Zoo{
	public Kangaroo() {
		this.kind = "캥거루"; // 종류
		this.name = "이름없음"; // 이름
		this.leg = 2; // 다리개수
		this.nose = "짧다"; // 코
		this.bodySize = "크다"; // 몸크기
		this.howEat = "나뭇잎을 뜯어 먹는다.";// 식사 방법
	}
}
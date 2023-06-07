package com.studty.lst;
import java.util.Scanner;

public class Study1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		// 자동차 객체를 생성한다.
		Benz benz = new Benz();
		
		// 입력을 받을 자동차의 대수정보를 받는다.
		benz.carCount(scan);
		
		//System.out.printf("자동차 대수 : %d\n", car.carcount);
		
		// 자동차의 정보를 입력 받는다.
		benz.saveCarInfo(scan);
		
		// 입력 받은 자동차의 정보를 출력한다.
		benz.outCarInfo();
		
		// 종목 별 총점을 출력한다.
		benz.printTotalPrint();
		
		// 종목 별 평균을 출력한다.
		benz.printAvg();
	}

}

// 자동차의 정보를 입력 받는다.
class Benz {
	// 자동차 대수를 저장할 변수
	int carCount;
	// 자동차 정보를 담을 배열의 변수
	Car [] carArray;
	// 자동차 대수를 입력받아 객체를 생성하는 메서드
	public void carCount(Scanner scan) {
		System.out.print("입력할 자동차의 대수를 입력하세요. : ");
		carCount = scan.nextInt();
	}
	// 자동차의 정보를 입력 받아 저장하는 메서드
	public void saveCarInfo(Scanner scan) {
		// 입력받은 수 만큼 객체를 생성한다.
		carArray = new Car[carCount];
		for(int i = 0; i < carCount; i++) {
			carArray[i] = new Car();
		}
		
		for(Car c1 : carArray) {
			c1.inputCarInfo(scan);
		}
	}
	// 자동차의 정보를 출력하는 메서드
	public void outCarInfo() {
		for(Car c1 : carArray) {
			c1.printCarInfo();
		}
	}
	// 종목별 총점을 출력하는 메서드
	int TotalfuelScore = 0;
	int TotalfuelTankScore = 0;
	int TotalsafetyScore = 0;
	public void printTotalPrint() {
		for(Car c1 : carArray) {
			TotalfuelScore += c1.fuel;
			TotalfuelTankScore += c1.fuelTank;
			TotalsafetyScore += c1.safetyScore;
		}
		System.out.println("종목별 총점");
		System.out.printf("연비 총점 : %d \n",TotalfuelScore);
		System.out.printf("연료통양 총양 : %d \n",TotalfuelTankScore);
		System.out.printf("안전점수 총점 : %d \n",TotalsafetyScore);
		System.out.println();
	}
	
	// 종목별 평균을 구하는 메서드
	public void printAvg() {
		System.out.println("종목별 평균");
		System.out.printf("연비 평균 : %d \n",TotalfuelScore/carCount);
		System.out.printf("연료통양 평균 : %d \n",TotalfuelTankScore/carCount);
		System.out.printf("안전점수 평균 : %d \n",TotalsafetyScore/carCount);
		System.out.println();
	}
	
}

// 자동차 정보를 입력 및 출력하는 클래스
class Car{
	
	String name; // 이름
	int km; // 달린 키로수
	int fuel; // 연비
	int fuelTank; // 연료통양
	int safetyScore; // 안전점수
	
	// 자동차 정보를 입력받는 메서드
	public void inputCarInfo(Scanner scan) {
		System.out.print("차 이름 : ");
		name = scan.next();
		System.out.print("달린 키로 수 : ");
		km = scan.nextInt();
		System.out.print("연비 : ");
		fuel = scan.nextInt();
		System.out.print("연료통양 : ");
		fuelTank = scan.nextInt();
		System.out.print("안전점수 : ");
		safetyScore = scan.nextInt();
		System.out.println();
	}
	
	// 자동차 정보를 출력하는 메서드
	public void printCarInfo() {
		System.out.printf("차 이름 : %s\n",name);
		System.out.printf("달린 키로 수 : %d\n",km);
		System.out.printf("연비 : %d\n",fuel);
		System.out.printf("연료통양 : %d\n",fuelTank);
		System.out.printf("안전점수 : %d\n",safetyScore);
		System.out.println();
	}
	
}
package study.car;

import java.util.Scanner;

public class CarClass {

	private Scanner scanner;

	// 자동차 객체들을 담을 배열
	private CarInfoClass[] carArray;
	// 자동차의 수
	private int carCount;

	public CarClass() {
		this.scanner = new Scanner(System.in);
		carArray = new CarInfoClass[1000];
		carCount = 0;
	}

	// 자동차을 추가하는 메서드
	public void addAnimal(int typeNumber) {

		// 자동차의 이름을 입력받는다.
		System.out.print("자동차의 이름은 무엇인가요? : ");
		String name = scanner.next();

		// 자동차 객체를 생성한다.
		CarInfoClass car = null;

		// typeNumber 에 따른 자동차 종류 클래스 입력 switch문
		switch (typeNumber) {
		case 1:
			// animal = new ElephantClass(name);
			car = new CarInfoClass() {
				@Override
				void startup() { // 시동 방법에 대한 메서드 오버라이딩
					// TODO Auto-generated method stub
					System.out.println("버튼을 눌러 시동을 겁니다.");
				}
			};
			car.carKind = "SUV";
			car.carPrice = "싸다";
			car.carColor = "빨간색";
			car.carName = name;
			break;
		case 2:
			car = new CarInfoClass() {
				@Override
				void startup() { // 시동 방법에 대한 메서드 오버라이딩
					// TODO Auto-generated method stub
					System.out.println("카드를 올려둡니다.");
				}
			};
			car.carKind = "세단";
			car.carPrice = "보통이다";
			car.carColor = "검은색";
			car.carName = name;
			break;
		case 3:
			car = new CarInfoClass() {
				@Override
				void startup() { // 시동 방법에 대한 메서드 오버라이딩
					// TODO Auto-generated method stub
					System.out.println("지문 인식");
				}
			};
			car.carKind = "슈퍼카";
			car.carPrice = "비싸다";
			car.carColor = "노란색";
			car.carName = name;
			break;
		}

		carArray[carCount] = car;
		carCount++;
	}

	// 입력한 자동차들의 정보를 출력하는 메서드
	public void printAnimalInfo() {
		// 자동차의 수 만큼 반복한다.
		for (int i = 0; i < carCount; i++) {
			CarInfoClass car = carArray[i];
			car.printInfo();
			car.startup();
			System.out.println();
		}
	}

	// 자동차의 타입을 입력받아 반환한다.
	public int inputCarType() {
		// 차 타입 번호를 입력받는다.
		System.out.println("어떤 자동차를 생산할까요?");
		System.out.print("1. SUV, 2. 세단, 3. 슈퍼카, 0. 종류 : ");
		int typeNumber = scanner.nextInt();
		return typeNumber;
	}

}

abstract class CarInfoClass {
	// 자동차 종류
	String carKind;
	// 자동차 가격
	String carPrice;
	// 자동차 색상
	String carColor;
	// 자동차 이름
	String carName;

	// 시동방법에 대한 추상 메서드
	abstract void startup();


	// 출력
	public void printInfo() {
		System.out.printf("자동차 종류 : %s\n", carKind);
		System.out.printf("이름 : %s\n", carName);
		System.out.printf("가격 : %s\n", carPrice);
		System.out.printf("색상  : %s\n", carColor);
	}
}

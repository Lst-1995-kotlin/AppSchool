package study;
import study.car.CarClass;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Car 클래스를 이용하여 만든 객체를 생성한다.
		CarClass carClass = new CarClass();
		
		// 입력받은 Car의 타입
        int typeNumber = 0;
        
        // 입력 받는 차 번호가 0이 아닌 동안 반복한다.
        do {
        	typeNumber = carClass.inputCarType();
        	
        	// 차 타입 번호가 0이 아니라면...
            if(typeNumber != 0) {
            	carClass.addAnimal(typeNumber);
            }
        	
        } while(typeNumber != 0);
        
        // 입력한 자동차들의 정보를 출력한다.
        carClass.printAnimalInfo();
	}

}
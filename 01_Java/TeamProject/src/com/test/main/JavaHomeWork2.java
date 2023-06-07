import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class JavaHomeWork2 {

	public static void main(String[] args) {
//		다음 조건에 맞는 자바 프로그램을 만들어주세요
//		- 학생의 정보를 관리하는 프로그램을 작성합니다.
//		- 학생의 정보는 이름, 학년, 국어, 영어, 수학 으로 구성됩니다.
//		- 프로그램을 시작하면 다음 메뉴가 나타납니다.
//		1. 학생정보 입력
//		2. 학생정보 검색
//		3. 과목별 총점 출력
//		4. 과목별 평균 출력
//		5. 종료
//
//		- 메뉴에서 1을 입력하면 학생정보를 입력받습니다.
//		- 학생 한명의 정보를 받으면 메뉴 화면이 나오도록 합니다.
//		- 학생정보 입력화면 구성은 자유입니다.
//
//		- 메뉴에서 2를 입력하면 학생 이름을 이름받습니다.
//		- 입력받은 이름의 학생의 정보를 출력합니다.
//		- 만약 이름에 "모두" 를 입력하면 모든 학생의 정보가 
//		- 출력 되도록 한다
//		- 동명의 이름의 학생이 어려명일 경우 모두 보여줍니다
//		- 이름 입력화면과 정보 출력화면은 자유롭게 구성합니다.
//
//		- 메뉴에서 3을 입력하면 각 과목별 총점을 출력합니다.
//		- 출력 화면 구성은 자유입니다.
//
//		- 메뉴에서 4를 입력하면 각 과목별 평균을 출력합니다.
//		- 출력 화면 구성은 자유입니다.
//
//		- 메뉴에서 5를 입력하면 프로그램을 종료합니다.
//
//		- 입력한 학생의 정보는 모두 파일로 저장해주시고 데이터도 파일에서 읽어와주세요
//
//		- 하나의 java 파일에 작성해주세요
//		- 작성한 java 파일을 제출해주세요
//		- 자바 파일의 이름은 자유
		
		
		// 이 예제의 핵심은 파일에서 객체를 읽어올 때 객체마다 ObjectInputStream을
		// 계속 생성하는다는 부분입니다.
		// searchStudentInfo 메서드를 참고해주세요

		SchoolClass schoolClass = new SchoolClass();

		while (true) {
			int inputNumber = schoolClass.getMenuNumber();
			if (inputNumber == 5) {
				break;
			}

			switch (inputNumber) {
			case 1:
				schoolClass.inputStudentInfo();
				break;
			case 2:
				schoolClass.searchStudentInfo();
				break;
			case 3:
				schoolClass.printTotal();
				break;
			case 4:
				schoolClass.printAvg();
				break;
			}
		}
	}
}

class SchoolClass {

	Scanner scanner;
	
	FileInputStream fis;
	ObjectInputStream ois;
	FileOutputStream fos;
	ObjectOutputStream oos;

	public SchoolClass() {
		try {
			scanner = new Scanner(System.in);
			



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getMenuNumber() {
		int inputNumber = 0;
		while (true) {
			System.out.println("1. 학생정보 입력");
			System.out.println("2. 학생정보 검색");
			System.out.println("3. 과목별 총점 출력");
			System.out.println("4. 과목별 평균 출력");
			System.out.println("5. 종료");
			System.out.print("번호 입력 : ");
			inputNumber = scanner.nextInt();

			if (inputNumber >= 1 && inputNumber <= 5) {
				break;
			} else {
				System.out.println("잘못 입력하였습니다");
			}
		}
		return inputNumber;
	}

	public void inputStudentInfo() {
		try {
			System.out.print("이름을 입력해주세요 : ");
			String name = scanner.next();
			
			System.out.print("학년을 입력해주세요 : ");
			int grade = scanner.nextInt();
			
			System.out.print("국어 점수를 입력해주세요 : ");
			int korean = scanner.nextInt();
			
			System.out.print("영어 점수를 입력해주세요 : ");
			int english = scanner.nextInt();
			
			System.out.print("수학 점수를 입력해주세요 : ");
			int math = scanner.nextInt();
			
			Student student = new Student(name, grade, korean, english, math);

			fos = new FileOutputStream("homework.dat", true);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(student);
			
			oos.close();
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchStudentInfo() {

		try {
			System.out.print("검색할 학생 이름을 입력해주세요 (모두 입력시 모든학생) : ");
			String searchName = scanner.next();			
			
			// 더이상 읽어올 것이 없으면 예외가 발생하는데 그걸 이용해
			// 반복을 중단합니다.
			
			fis = new FileInputStream("homework.dat");

			while(true) {
				// ObjectInputStream을 객체를 읽어올 때 마다 새롭게 생성해줍니다.
				ois = new ObjectInputStream(fis);
				Student student = (Student) ois.readObject();
				
				
				if(searchName.equals("모두")) {
					student.printStudent();
				} else if(searchName.equals(student.getName())) {
					student.printStudent();
				}
			}
			
		}catch(Exception e) {
			// e.printStackTrace();
			try {
				ois.close();
				fis.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void printTotal() {
		int koreanTotal = 0;
		int englishTotal = 0;
		int mathTotal = 0;
		
		
		try {			
			// 더이상 읽어올 것이 없으면 예외가 발생하는데 그걸 이용해
			// 반복을 중단합니다.
			
			fis = new FileInputStream("homework.dat");
			
			

			while(true) {
				// ObjectInputStream을 객체를 읽어올 때 마다 새롭게 생성해줍니다.
				ois = new ObjectInputStream(fis);
				Student student = (Student) ois.readObject();
				
				koreanTotal += student.getKorean();
				englishTotal += student.getEnglish();
				mathTotal += student.getMath();
			}
			
		}catch(Exception e) {
			// e.printStackTrace();
			try {
				ois.close();
				fis.close();
				
				System.out.printf("국어 총점 : %d\n", koreanTotal);
				System.out.printf("영어 총점 : %d\n", englishTotal);
				System.out.printf("수학 총점 : %d\n", mathTotal);
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void printAvg() {
		int koreanTotal = 0;
		int englishTotal = 0;
		int mathTotal = 0;
		int studentCnt = 0;
		
		try {			
			// 더이상 읽어올 것이 없으면 예외가 발생하는데 그걸 이용해
			// 반복을 중단합니다.
			
			fis = new FileInputStream("homework.dat");
			
			

			while(true) {
				// ObjectInputStream을 객체를 읽어올 때 마다 새롭게 생성해줍니다.
				ois = new ObjectInputStream(fis);
				Student student = (Student) ois.readObject();
				
				koreanTotal += student.getKorean();
				englishTotal += student.getEnglish();
				mathTotal += student.getMath();
				
				studentCnt++;
			}
			
		}catch(Exception e) {
			// e.printStackTrace();
			try {
				ois.close();
				fis.close();
				
				System.out.printf("국어 평균 : %d\n", koreanTotal / studentCnt);
				System.out.printf("영어 평균 : %d\n", englishTotal / studentCnt);
				System.out.printf("수학 평균 : %d\n", mathTotal / studentCnt);
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
 
}

class Student implements Serializable {
	private String name;
	private int grade;
	private int korean;
	private int english;
	private int math;

	public Student(String name, int grade, int korean, int english, int math) {
		super();
		this.name = name;
		this.grade = grade;
		this.korean = korean;
		this.english = english;
		this.math = math;
	}
	
	public void printStudent() {
		System.out.printf("이름 : %s\n", name);
		System.out.printf("학년 : %d\n", grade);
		System.out.printf("국어점수 : %d\n", korean);
		System.out.printf("영어점수 : %d\n", english);
		System.out.printf("수학점수 : %d\n", math);
		System.out.println();
	}
	
	public String getName() {
		return name;
	}

	public int getKorean() {
		return korean;
	}

	public int getEnglish() {
		return english;
	}

	public int getMath() {
		return math;
	}
	
	
}
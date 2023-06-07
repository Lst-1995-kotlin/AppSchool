// 9팀
// 홍승현, 한귀식, 윤희서, 이성태

package com.test.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentMgr stMgr = new StudentMgr();

		Scanner scan = new Scanner(System.in);
		// 입력 숫자 담을 변수.
		int inputNum;
		do {
			// 메뉴 출력.
			System.out.println("1. 학생정보 입력");
			System.out.println("2. 학생정보 검색");
			System.out.println("3. 과목별 총점 출력");
			System.out.println("4. 과목별 평균 출력");
			System.out.println("5. 종료");

			// 키 입력 받기.
			inputNum = scan.nextInt();
			System.out.println();

			switch (inputNum) {
			case 1: // 1. 학생정보 입력
				stMgr.InputData(scan);
				break;
			case 2: // 2. 학생정보 검색
				stMgr.SelectStudent(scan);
				break;
			case 3: // 3. 과목별 총점 출력
				// 총점 계산, 출력.
				stMgr.PrintPointTotal();
				break;
			case 4: // 4. 과목별 평균 출력
				// 평균 계산, 출력.
				stMgr.PrintPointAvg();
				break;
			case 5: // 5. 종료
				stMgr.EndMethod();
				System.out.println("프로그램 종료.");
				break;
			}

			System.out.println("--------------------------");
		} while (inputNum != 5);

		scan.close();
	}

}

// 학생 관리 클래스.
class StudentMgr {

	final String FILE_NAME = "studentInfo.txt";
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;

	public void InputData(Scanner scan) {
		// 입력받기.
		System.out.print("학생의 이름은? ");
		String name = scan.next();
		System.out.print("학생의 학년은? ");
		Integer grade = scan.nextInt();
		System.out.print("학생의 국어점수는? ");
		Integer kor = scan.nextInt();
		System.out.print("학생의 영어점수는? ");
		Integer eng = scan.nextInt();
		System.out.print("학생의 수학점수는? ");
		Integer math = scan.nextInt();

		// 학생 데이터를 생성한다.
		Student st = new Student(name, grade, kor, eng, math);
		// 로컬에 저장한다.
		SaveData(st);
	}

	public void SelectStudent(Scanner scan) {
		// 검색할 학생 이름 입력받기.
		System.out.print("정보를 검색할 학생의 이름은? ('모두'는 전체 검색) ");
		String name = scan.next();

		// 로컬에서 데이터 가져오기.
		ArrayList<Student> st = LoadData();
		if (st == null) {
			// System.out.println("가져올 데이터가 없습니다.");
		} else if (name.equals("모두")) {
			for (Student s : st) {
				s.printStudentInfo();
			}
		} else {
			for (Student s : st) {
				if (s.name.equals(name)) {
					s.printStudentInfo();
				}
			}
		}
	}

	public void PrintPointTotal() {
		// 로컬에서 데이터 가져오기.
		ArrayList<Student> st = LoadData();
		if (st == null) {
			// System.out.println("가져올 데이터가 없습니다.");
		} else {
			// 총점 계산, 출력.
			Integer kor = 0;
			Integer eng = 0;
			Integer math = 0;

			for (Student s : st) {
				kor += s.kor;
				eng += s.eng;
				math += s.math;
			}
			System.out.printf("과목별 총점은 국어 %d점, 영어 %d점, 수학 %d점 입니다.\n", kor, eng, math);
		}
	}

	public void PrintPointAvg() {
		// 로컬에서 데이터 가져오기.
		ArrayList<Student> st = LoadData();
		if (st == null) {
			// System.out.println("가져올 데이터가 없습니다.");
		} else {
			// 평균 계산, 출력.
			Double kor = 0.0;
			Double eng = 0.0;
			Double math = 0.0;

			for (Student s : st) {
				kor += s.kor;
				eng += s.eng;
				math += s.math;
			}

			int stCount = st.size();
			kor /= stCount;
			eng /= stCount;
			math /= stCount;

			System.out.printf("과목별 평균은 국어 %.0f점, 영어 %.0f점, 수학 %.0f점 입니다.\n", kor, eng, math);
		}
	}

	// 로컬에서 저장되어있는 학생의 데이터를 가져온다.
	private ArrayList<Student> LoadData() {
		ArrayList<Student> stList = new ArrayList<Student>();
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			// 객체를 복원한다.
			while (true) {
				try {
					Student t = (Student) ois.readObject();
					stList.add(t);
				} catch (Exception e) {
					break;
				}
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			//System.out.println("파일이 없습니다!");
		} catch (EOFException e) {
			// System.out.println("파일을 끝까지 읽었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (stList.size() == 0) {
			//System.out.println("학생 데이터가 0개 입니다.");
			return null;
		} else {
			return stList;
		}
	}
	private void SaveData(Student student) {
		// 저장되어 있는 데이터를 먼저 읽어온다.
		ArrayList<Student> st = LoadData();
		if (st == null) {
			st = new ArrayList<Student>();
		}
		//System.out.printf("st.size() = %d\n", st.size());
		// 학생을 추가한다.
		st.add(student);

		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// 로컬 파일에 전체 학생 데이터를 덮어쓰기 한다.
			for (Student s : st) {
				oos.writeObject(s);
			}

			oos.flush();
			oos.close();
			fos.close();

			System.out.println("학생 데이터 저장 완료.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	// 로컬 저장소에 저장한다. studentInfo.txt
//	private void SaveData(Student student) {
//	
////		// 저장되어 있는 데이터를 먼저 읽어온다.
//		ArrayList<Student> st = LoadData();
//		
//		if(st == null) {
//			st = new ArrayList<Student>();
//		}
//		st.add(student);
//		try {
//			
//			if (oos == null) {
//				fos = new FileOutputStream(FILE_NAME);
//				oos = new ObjectOutputStream(fos);
//			}
//			for(Student s : st) {
//				oos.writeObject(s);
//			}
//		}catch (Exception e) {
//			
//		}
//		
//
//		
//	}
		


	public void EndMethod() {
		try {
			if (oos != null) {
				oos.flush();
				oos.close();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// 학생의 데이터.
class Student implements Serializable {
	String name;
	Integer grade;
	Integer kor;
	Integer eng;
	Integer math;

	public Student(String name, Integer grade, Integer kor, Integer eng, Integer math) {
		this.name = name;
		this.grade = grade;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	// 학생 1명의 데이터 출력함수.
	public void printStudentInfo() {
		// 학생의 정보를 출력해주세요.
		System.out.printf("이름 : %s, 학년 : %d, 국어점수 : %d, 영어점수 : %d, 수학점수 : %d\n", name, grade, kor, eng, math);
	}
}

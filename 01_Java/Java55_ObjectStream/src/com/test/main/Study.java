package com.test.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Study {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		saveObj();
		loadObj();
		
	}

	// 파일에 저장하는 메서드
	public static void saveObj() {
		try {
			
			// 파일에 저장하는 스트림 생성
			FileOutputStream fos = new FileOutputStream("test1.txt");
			// 파일에 저장할 객체를 내보내는 스트림 생성
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// 저장할 학생들 객체 생성.
			Student s1 = new Student("one",1,1);
			Student s2 = new Student("two",2,2);
			Student s3 = new Student("three",3,3);
			
			// 파일에 객체를 저장(쓴다)
			oos.writeObject(s1);
			oos.writeObject(s2);
			oos.writeObject(s3);
			
			// 남아 있는 데이터가 있을 수 있으니 남아있다면 파일에 모두 저장.
			oos.flush();
			// 저장하기 위한 스트림을 닫아준다.
			// 닫는 순서는 역순
			oos.close();
			fos.close();

			System.out.println("저장완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일에 있는 정보를 가져오는 메서드
	public static void loadObj() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			// 파일에 저장된 정보 가져오는 스트림 생성.
			fis = new FileInputStream("test1.txt");
			ois = new ObjectInputStream(fis);
			
			while(true) {
				// 읽어온 데이터는 바이트배열 형식으로 되어 있으므로 형변환을 해야한다.
				Student readStd = (Student)ois.readObject();
				readStd.printInfo();
			}
		}catch(EOFException e) {
			System.out.println("더 이상 읽어올 학생이 없습니다.");
			
			if(ois != null)
				try {
					ois.close();
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}


// 객체 직렬화를 위해 Serializable 인터페이스를 구현한 클래스

class Student implements Serializable{
	
	String name;
	Integer age;
	Integer grade;
	
	public Student(String name,Integer age,Integer grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}
	
	public void printInfo() {
		System.out.printf("이름 : %s\n",name);
		System.out.printf("나이 : %s\n",age);
		System.out.printf("학급 : %s\n",grade);
	}
	
}







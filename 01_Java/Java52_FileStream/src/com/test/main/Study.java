package com.test.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Study {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 데이터 저장
		saveData();
		saveData2();
		loadData();	
	}

	
	// 데이터를 저장하는 메서드
	public static void saveData() {
		
		try {
			// 파일을 쓰기위한 스트림 생성
			// 파일이 없다면 파일을 생성함.
			FileOutputStream fos = new FileOutputStream("study.txt");
			
			// 데이터 생성
			String value = "데이터1";
			
			// 데이터를 바이트 형태로 가공한다.
			byte [] data1 = value.getBytes();
			
			// 데이터 작성
			fos.write(data1);
			
			// 출력버퍼에 데이터가 남아 있는 것을 방지
			fos.flush();
			
			// 스트림을 닫아준다.
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 데이터를 저장하는 메서드
	public static void saveData2() {
		
		try {
			// 파일을 쓰기위한 스트림 생성
			// 파일이 없다면 파일을 생성함.
			// 뒤에 true를 보내주면, 기존 파일이 있을 경우 내용을 추가한다.
			FileOutputStream fos = new FileOutputStream("study.txt",true);
			
			// 데이터 생성
			String value = "데이터2";
			
			// 데이터를 바이트 형태로 가공한다.
			byte [] data1 = value.getBytes();
			
			// 데이터 작성
			fos.write(data1);
			
			// 출력버퍼에 데이터가 남아 있는 것을 방지
			fos.flush();
			
			// 스트림을 닫아준다.
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
	// 데이터를 가져오는 메서드
	public static void loadData() {
		try {
			// 데이터를 읽어오는 스트림.
			FileInputStream fis = new FileInputStream("study.txt");
			
			// 읽어올 데이터의 양을 구한다.
			int byteSize = fis.available();
			
			// 데이터를 저장할 byte타입 배열을 만든다.
			byte[] loadArr = new byte[byteSize];
			
			// 데이터를 읽어온다.
			fis.read(loadArr);
			// 스트림을 닫아준다.
			fis.close();
			
			// 읽어온 데이터를 String 객체로 생성한다.
			String str = new String(loadArr);
			System.out.println(str);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
package com.study.main;

public class StudyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String 는 자바에서 문자열을 관리하기 위해 제공되는 클래스이다.
		// " "로 묶어준 문자열도 String 객체 입니다.
		String str1 = "study";
		String str2 = new String("study");
		
		if(str1 == str2)
			System.out.println("== 연산자 결과 : 같다");
		else
			System.out.println("== 연산자 결과 : 다르다");
		
		// equals 두 객체의 값이 같다면 true 다르다면 false를 반환한다.
		if(str1.equals(str2))
			System.out.println("equals 결과 : 같다");
		else 
			System.out.println("equals 결과 : 다르다");
		
		
		// toUpperCase 소문자를 대문자로 변경하여 저장한다.
		String str3 = "abcd";
		String str4 = str3.toUpperCase();
		System.out.printf("str3.toUpperCase() 결과 : %s\n", str4);
		
		// toLowerCase 대문자를 소문자로 변경하여 저장한다.
		String str5 = "ABCD";
		String str6 = str5.toLowerCase();
		System.out.printf("str5.toLowerCase() 결과 : %s\n", str6);
		
		// replace 해당 문자를 다른 문자로 바꾸어 저장한다.
		String str7 = "abcdef";
		String str8 = str7.replace("c", "CCCCCC");
		System.out.printf("str7.replace() 결과 : %s\n", str8);
		
		// indexOf : 지정된 문자열의 위치를 반환한다.
		String str9 = "9876543210";
		System.out.printf("str9.indexOf(\"2\") 결과 : %s\n",str9.indexOf("2"));
		
		// startsWith : ~로 시작하는지에 대해 boolean 형으로 반환한다.
		// endsWith : ~로 끝나는지에 대해 boolean 형으로 반환한다.
		System.out.printf("str9.startsWith(\"2\") 결과 : %s\n",str9.startsWith("2"));
		System.out.printf("str9.startsWith(\"9\") 결과 : %s\n",str9.startsWith("9"));
		
		System.out.printf("str9.endsWith(\"7\") 결과 : %s\n",str9.endsWith("7"));
		System.out.printf("str9.endsWith(\"0\") 결과 : %s\n",str9.endsWith("0"));
		
		// length : 문자열의 길이를 반환한다. 즉 글자수를 반환한다.
		System.out.printf("str9.length : %d\n", str9.length());
		
		// substring : 지정한 위치에 있는 문자열 일부를 가져온다
		String str10 = "가나다라마바사아자";
		System.out.printf("str10.substring(5) : %s\n", str10.substring(5));
		System.out.printf("str10.substring(1, 5) : %s\n", str10.substring(1, 5));
		// 가:0 나:1 다:2 .... 자:8 위치는 배열처럼 생각하면 된다.
		
		// trim : 문자열의 좌우 공백을 제거한다. 문자열 사이의 공백은 제거하지 않는다.
		String str11 = "           가            나              ";
		System.out.printf("str11.trim() :%s\n", str11.trim());
	}

}

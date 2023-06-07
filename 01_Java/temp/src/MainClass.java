public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // s1이라는 객체를 생성하였습니다.
        // s1이라는 객체는 StudentClass을 이용하여 새로운 StudentClass 형태를 갖는
        // 객체 입니다.
        // 해당 말이 헷갈릴 수 있는데 비유를 하자면 벤츠 자동차 생산라인을 거쳐 
        // 완성된 벤츠 자동차라는 것입니다.
        // 클래스는 비유로 하자면 벤츠 자동차 생산라인, 객체는 완성된 벤츠 자동차 입니다.
        
		StudentClass s1 = new StudentClass();
        
        // 객체 참조 변수를 통한 멤버 변수 접근
        // 메서드와 동일하게 . 을 이용하여 접근한다.
        System.out.printf("국어점수 : %d\n", s1.koreaScore);
		System.out.printf("영어점수 : %d\n", s1.englishScore);
		System.out.printf("평균점수 : %d\n", s1.getAvg());
		
	}
}

class StudentClass {
	// koreaScore, englishScore 는 멤버 변수(데이터) 입니다.
    // 멤버 변수의 경우 같은 클래스 내 작성한 멤버 메서드(기능)에서 사용이 가능합니다.
    int koreaScore = 50;
    int englishScore = 50;

	// 평균을 구하는 메서드(기능) 입니다.
    // 멤버 변수의 경우 같은 클래스 내 작성한 멤버 메서드(기능)에서 사용이 가능합니다.
    // 따라서 koreaScore, englishScort의 값을 가져와 평균을 구한 후
    // int 형으로 반환 합니다.
    public int getAvg(){ 
        int avg = (koreaScore + englishScore) / 2;
        return avg;
    }

}
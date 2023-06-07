
public class JavaHomeWork1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 문제1)
        // 1 + (1 + 2) + (1 + 2 + 3) + (1 + 2 + 3 + 4)
        // + (1 + 2 + 3 + 4 + 5) + ... + (1 + 2 + ... + 9 + 10)
        // 의 총 합을 출력하세요
		
		int total1 = 0;
		
		for(int k = 1 ; k <= 10 ; k++) {
			for(int i = 1 ; i <= k ; i++) {
				total1 += i;
			}
		}
		System.out.println(total1);

        // 정답 : 220

        // 문제2)
        // 1 + (-2) + 3 + (-4) + 5 + (-6) + 7 + (-8) + ...
        // 이런식으로 더했을 때 몇 까지 더해야지 총 합이 100 이상이 되는지 구하세요.
		
		int number2 = 1;
		int total2 = 0;
		int sign2 = 1;
		
		while (true) {
			total2 = total2 + (number2 * sign2);
			
			if(total2 >= 100) {
				break;
			}
			
			number2++;
			sign2 *= -1;
		}
		
		System.out.println(number2);

        // 정답 : 199

        // 문제3)
        // 방정식 2x + 4y = 10 에서 가능한 모든 x, y 의 조합을 출력하세요
        // 단, x는 0 ~ 10, y는 0 ~ 10 까지 이다.

        // 정답
        // x : 5, y : 0
        // x : 3, y : 1
        // x : 1, y : 2
		
		for(int x = 0 ; x <= 10 ; x++) {
			for(int y = 0 ; y <= 10 ; y++) {
				int total3 = (2 * x) + (4 * y);
				if(total3 == 10) {
					System.out.printf("x : %d, y : %d\n", x, y);
				}
			}
		}
	}

}

import java.util.Scanner

//장난감 공장
//
//생산할 장난감의 종류를 선택해주세요
//1. 로보트 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝
//
//잘못된 번호를 입력했을 경우 잘못 입력하였습니다를 출력한다.
//
//모든 입력이 완료되면 다음과 같이 출력한다.
//
//총 : 00개
//로보트 장난감 : 00개
//레고 : 00개
//BB탄 총 : 00개
//잠만보인형 : 00개
//
//로보트 장난감
//가격 : 5000원
//크기 : 로보트만큼 크다
//
//레고
//가격 : 50000원
//크기 : 레고만큼 크다
//
//BB탄 총
//가격 : 10000원
//크기 : BB탄 총 만큼 크다
//
//잠만보인형
//가격 : 20000원
//크기 : 잠만보 만큼 크다
//
//생산된 장난감 총 가격 : 0000000원
//생산된 장난감 평균 가격 : 000000원

// 생산된 장난감을 저장할 리스트


fun main() {
    // 장난감 리스트 저장
    val choiceList = choiceToy()

    // 장난감 별 개수 구한 후 출력한다.
    countGet(choiceList)

    // 생산된 장난감의 정보를 출력하는 함수

    // 생산된 장난감의 총 가격과 평균 가격을 구하고 출력한다.
    priceGet(choiceList)
}

// 생산된 장난감의 정보를 출력하는 함수
fun printToy(list : ArrayList<Toy>){
    for(toy in list){
        toy.printMethod()
    }
}

// 생산된 장난감의 총 가격과 평균을 구하고 출력하는 함수
fun priceGet(list : ArrayList<Toy>){
    // 총 가격을 저장할 변수
    var sum = 0
    // 반복문 실행
    for(toy in list){
        // sum 변수에 리스트에 저장된 장난감의 가격을 누적한다.
        sum += toy.price
    }

    val avg  = sum/list.size

    //생산된 장난감 총 가격 : 0000000원
    //생산된 장난감 평균 가격 : 000000원

    println("생산된 장난감 총 가격 : ${sum}원")
    println("생산된 장난감 평균 가격 : ${avg}원")

}


// 장난감의 개수를 구하고 출력하는 함수
fun countGet(list : ArrayList<Toy>) {
    // 장난감의 총 합을 저장할 변수
    val sum = list.size
    // 로보트 개수 저장할 변수
    var robot = 0
    // 레고 저장할 변수
    var rego = 0
    // BB탄 총 저장할 변수
    var bbGun = 0
    // 잠만보인형 저장할 변수
    var jam = 0

    // 장난감의 개수를 파악하는 반복문
    for(toy in list){
        when(toy.name){
            "로보트 장난감" -> robot++
            "레고" -> rego++
            "BB탄 총" -> bbGun++
            "잠만보인형" -> jam++
        }
    }
//총 : 00개
//로보트 장난감 : 00개
//레고 : 00개
//BB탄 총 : 00개
//잠만보인형 : 00개
    println("총 : ${sum}개")
    println("로보트 장난감 : ${robot}개")
    println("레고 : ${rego}개")
    println("BB탄 총 : ${bbGun}개")
    println("잠만보인형 : ${jam}개")

}


// 장난갑의 종류를 선택받은 리스트를 반환하는 함수
fun choiceToy() : ArrayList<Toy> {
    val scan = Scanner(System.`in`)
    // 선택 받은 장난감들을 저장할 리스트
    val toyList = ArrayList<Toy>()
    // 선택받은 장난감의 타입을 저장할 변수
    var select = 0

    // 종류를 입력받는 반복문
    do{
        //생산할 장난감의 종류를 선택해주세요
        //1. 로보트 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝
        print("1. 로보트 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝 :")
        // 선택 받은 장난감 번호를 저장
        select = scan.nextInt()

        // 잘못된 번호를 입력받은 경우
        //잘못된 번호를 입력했을 경우 잘못 입력하였습니다를 출력한다.
        if(select !in 0 .. 4){
            println("잘못 입력하였습니다")
        } else { // 정상적으로 입력 하였을 경우
            when(select){
                //로보트 장난감
                //가격 : 5000원
                //크기 : 로보트만큼 크다
                //
                //레고
                //가격 : 50000원
                //크기 : 레고만큼 크다
                //
                //BB탄 총
                //가격 : 10000원
                //크기 : BB탄 총 만큼 크다
                //
                //잠만보인형
                //가격 : 20000원
                //크기 : 잠만보 만큼 크다
                0 -> break
                1 -> {
                    toyList.add(Toy("로보트 장난감", 5000, "로보트만큼 크다"))
                }

                2 ->{
                    toyList.add(Toy("레고", 50000, "레고만큼 크다"))
                }
                3 ->{
                    toyList.add(Toy("BB탄 총", 10000, "BB탄 총 만큼 크다"))
                }
                else ->{ // 4
                    toyList.add(Toy("잠만보인형", 20000, "잠만보 만큼 크다"))
                }
            }
        }

    }while(select != 0)
    // 반복문 조건 입력받은 타입이 0이 아닐 때까지

    return toyList
}

// 장난감 정보 저장 클래스
// 전달 받은 매개 변수로 생산하는 장난감 종류 분류


class Toy(val name: String, val price: Int, val size: String) {
    fun printMethod(){
        //BB탄 총
        //가격 : 10000원
        //크기 : BB탄 총 만큼 크다
        println(name)
        println("가격 : ${price}원")
        println("크기 : $size")
    }
}



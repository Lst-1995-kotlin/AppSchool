import java.util.Scanner

fun main(){

    // 음료수를 고른다.
//    음료수를 고르세요
//    1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) : 1

    // 사용자에게 음료 번호를 받는다.
    val scan = Scanner(System.`in`)
    var select = 0
    do {
        print("1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) :")
        // 입력 받은 값으로 select 값 갱신
        select = scan.nextInt()

        // 제대로 입력을 안했을 경우 메시지 출력
        if(!(select in 1 .. 4)){
            println("다시 입력해주세요")
        }

    } while(!(select in 1 .. 4))
    // while 반복 조건
    // 만약 그외의 번호를 입력하면.... 다시 입력해주세요 라고 출력하고 위의 메뉴가 다시 나오게 한다.
    // 1 ~ 4 까지의 범위가 아니라면 반복한다.

    //println(select)

    // 구매하려는 음료수의 금액을 찾는다.
    var needMoney = 0
    when(select){
        1 -> needMoney += Coke().price
        2 -> needMoney += Soda().price
        3 -> needMoney += Coffee().price
        else -> needMoney += Icetea().price
    }

    // 사용자에게 돈을 입력받는다.
    var insertMoney = 0
    // 동전을 입력받아 insertMoney의 값을 누적한다.
    // insertMoney의 값이 needMoney 보다 많아지면 반복문 종료.
    while(needMoney > insertMoney){
        // 현재 0원/부족 1000원 출력
        println("현재 ${insertMoney}원/부족 ${needMoney-insertMoney}원 ")
        print("동전을 넣어주세요 : ")
        // 입력받은 돈을 갱신한다.
        insertMoney += scan.nextInt()
    }
    println("현재 ${insertMoney}원/부족 0원 ")

    // 처음에 선택한 음료수의 정보를 출력한다.
    when(select){
        in 1 .. 4 -> Coke().printInfo()
        2 -> Soda().printInfo()
        3 -> Coffee().printInfo()
        else -> Icetea().printInfo()
    }

}

// 콜라 클래스
class Coke{
    val price = 1000
    val amount = "300ml"
    val factory = "코카콜라"

    // 정보 출력하는 멤버 메서드
    fun printInfo() {
        println(
            """콜라는 ${price}원 이고
            |양은 $amount 입니다.
            |회사는 $factory 입니다.
        """.trimMargin()
        )
    }
}

// 사이다 클래스
class Soda{
    val price = 1000
    val amount = "300ml"
    val factory = "코카콜라"

    // 정보 출력하는 멤버 메서드
    fun printInfo(){
        println("""콜라는 ${price}원 이고
            |양은 $amount 입니다.
            |회사는 $factory 입니다.
        """.trimMargin())
    }

}

// 커피 클래스
class Coffee{
    val price = 1000
    val amount = "300ml"
    val factory = "코카콜라"

    // 정보 출력하는 멤버 메서드
    fun printInfo(){
        println("""콜라는 ${price}원 이고
            |양은 $amount 입니다.
            |회사는 $factory 입니다.
        """.trimMargin())
    }

}

// 복숭아 아이스티 클래스
class Icetea{
    val price = 1000
    val amount = "300ml"
    val factory = "코카콜라"

    // 정보 출력하는 멤버 메서드
    fun printInfo(){
        println("""콜라는 ${price}원 이고
            |양은 $amount 입니다.
            |회사는 $factory 입니다.
        """.trimMargin())
    }

}
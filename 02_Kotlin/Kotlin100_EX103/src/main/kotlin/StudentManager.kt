import java.util.*

class StudentManager(val scanner: Scanner) {

    // 점수를 입력받는다.
    fun inputPoint(){
        for(idx in 1..3){
            println()
            print("국어점수 : ")
            val korean = scanner.nextInt()
            print("영어점수 : ")
            val english = scanner.nextInt()
            print("수학점수 : ")
            val math = scanner.nextInt()
        }
    }

    // 점수를 출력한다.
    fun printPoint(){
        for(idx in 1..3){
            println()
            println("국어점수 : 100")
            println("영어점수 : 200")
            println("수학점수 : 300")
        }
    }

    // 과목별 총점과 평균을 출력한다.
    fun printAgg(){
        println()
        println("국어총점 : 300")
        println("영어총점 : 400")
        println("수학총점 : 500")
        println("국어평균 : 100")
        println("영어평균 : 50")
        println("수학평균 : 30")
    }
}
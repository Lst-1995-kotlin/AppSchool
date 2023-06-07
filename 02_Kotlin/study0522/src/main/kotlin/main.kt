import java.util.Scanner

fun main(){
    School().run{
        makeStudent()
        printStudentInfo()
        printStudentTotalAndAvg()
    }
}

class School {
    val scanner = Scanner(System.`in`)

    val studentArray = ArrayList<Student>()

    fun makeStudent(){
        var stop = 0
        while(stop != 2){
            println("학생 정보를 입력해주세요")
            print("이름 : ")
            val name = scanner.next()
            print("국어 : ")
            val kor = scanner.nextInt()
            print("영어 : ")
            val eng = scanner.nextInt()
            print("수학 : ")
            val math = scanner.nextInt()

            val std = Student(name,kor,eng,math)
            studentArray.add(std)

            while(true){
                print("계속 입력하시겠습니까?(1. 예, 2. 아니오) : ")
                stop = scanner.nextInt()
                if(stop in 1 .. 2) break
                println("잘못 입력하였습니다.")
            }

        }
        println("-----------------------")

    }

    fun printStudentInfo(){
        for(std in studentArray){
            std.run{
                printInfo()
                println("총점 : ${kor + eng + math}")
                println("평균 : ${(kor + eng + math)/3.0}")
                println("-----------------------")
            }
        }

    }

    fun printStudentTotalAndAvg(){
        var korTotal = 0
        var engTotal = 0
        var mathTotal = 0

        for(std in studentArray){
            std.run{
                korTotal += kor
                engTotal += eng
                mathTotal += math
            }
        }

        println("국어 전체 총점 : ${korTotal}점")
        println("영어 전체 총점 : ${engTotal}점")
        println("수학 전체 총점 : ${mathTotal}점")

        println("국어 전체 평균 : ${(korTotal.toDouble()/studentArray.size)}점")
        println("영어 전체 평균 : ${(engTotal.toDouble()/studentArray.size)}점")
        println("수학 전체 평균 : ${(mathTotal.toDouble()/studentArray.size)}점")


    }
}

class Student(val name: String, val kor: Int, val eng: Int, val math: Int){

    val printInfo = {
        println("이름 : $name")
        println("국어 : $kor")
        println("영어 : $eng")
        println("수학 : $math")
    }

}
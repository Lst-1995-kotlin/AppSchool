import java.io.*
import java.util.*

class StudentManager(val scanner: Scanner) {

    // 학생의 정보를 담는 클래스
    data class StudentInfo(var korean:Int, var english:Int, var math:Int) : Serializable

    // 학생 정보를 담을 리스트
    val studentList = mutableListOf<StudentInfo>()

    fun inputPoint(){

        // 학생 정보를 담을 리스트 초기화
        studentList.clear()

        for(idx in 1..3){
            println()
            print("국어점수 : ")
            val korean = scanner.nextInt()
            print("영어점수 : ")
            val english = scanner.nextInt()
            print("수학점수 : ")
            val math = scanner.nextInt()

            val studentInfo = StudentInfo(korean, english, math)
            studentList.add(studentInfo)
        }

        saveStudentInfo()
    }

    // 파일에 저장한다
    fun saveStudentInfo(){
        val fos = FileOutputStream("test.dat")
        val oos = ObjectOutputStream(fos)

        for(studentInfo in studentList){
            oos.writeObject(studentInfo)
        }

        oos.flush()
        oos.close()
        fos.close()
    }

    // 파일에 저장된 정보를 읽어온다.
    fun loadStudentInfo(){
        val fis = FileInputStream("test.dat")
        val ois = ObjectInputStream(fis)

        // 리스트 초기화
        studentList.clear()

        try{
            while(true){
                val studentInfo = ois.readObject() as StudentInfo
                studentList.add(studentInfo)
            }
        }catch(e:Exception){

        }
    }

    fun printPoint(){

        // 학생 정보를 읽어온다.
        loadStudentInfo()

        for(studentInfo in studentList){
            println()
            println("국어점수 : ${studentInfo.korean}")
            println("영어점수 : ${studentInfo.english}")
            println("수학점수 : ${studentInfo.math}")
        }
    }

    fun printAgg(){

        // 학생 정보를 읽어온다.
        loadStudentInfo()

        // 총점을 구한다.
        var koreanTotal = 0
        var englishTotal = 0
        var mathTotal = 0

        for(studentInfo in studentList){
            koreanTotal += studentInfo.korean
            englishTotal += studentInfo.english
            mathTotal += studentInfo.math
        }

        // 평균을 구한다.
        val koreanAvg = koreanTotal / studentList.size
        val englishAvg = englishTotal / studentList.size
        val mathAvg = mathTotal / studentList.size

        println()
        println("국어총점 : $koreanTotal")
        println("영어총점 : $englishTotal")
        println("수학총점 : $mathTotal")
        println("국어평균 : $koreanAvg")
        println("영어평균 : $englishAvg")
        println("수학평균 : $mathAvg")
    }
}
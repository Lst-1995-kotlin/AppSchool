import com.sun.tools.javac.Main
import java.util.Scanner
import kotlin.system.exitProcess

// 5. 다음 조건에 맞는 값들을 확인하고 그와 관련된 클래스를 만들어준다.
// - 각 화면을 구성하기 위해 필요한 데이터
// - 사용자가 입력하는 데이터
// - 발생되는 데이터
// - 그외에 예측이 가능한 데이터

//[점수 입력 상태]
//국어, 영어, 수학 점수
//
//[각 과목별 점수 출력 상태]
//국어, 영어, 수학 점수
//
//[각 과목별 총점과 평균을 출력 상태]
//국어 총점, 영어 총점, 수학 총점
//국어 평균, 영어 평균, 수학 평균

// 국어, 영어, 수학 점수는 입력받은 데이터이고 다음에 써야 하기 때문에
// 저장한다.
// 각 과목별 총점과 평균은 구하면 되기 때문에 저장하지 않는다.

// 학생에 대한 정보이므로 StudentManager에 StudentInfo라는 클래스로 정의했다

// 다음은 Kotlin100_EX105를 보세요

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

class MainClass{
    var programState = ProgramState.PROGRAM_STATE_INPUT_INFO

    val scanner = Scanner(System.`in`)
    val studentManager = StudentManager(scanner)

    fun running(){
        while(true) {
            when (programState) {
                ProgramState.PROGRAM_STATE_INPUT_INFO -> {
                    studentManager.inputPoint()
                    programState = ProgramState.PROGRAM_STATE_SHOW_INFO
                }

                ProgramState.PROGRAM_STATE_SHOW_INFO -> {
                    studentManager.printPoint()
                    programState = ProgramState.PROGRAM_STATE_SHOW_AGG
                }

                ProgramState.PROGRAM_STATE_SHOW_AGG -> {
                    studentManager.printAgg()
                    exitProcess(0)
                }
            }
        }
    }
}

enum class ProgramState{
    PROGRAM_STATE_INPUT_INFO,
    PROGRAM_STATE_SHOW_INFO,
    PROGRAM_STATE_SHOW_AGG
}
import com.sun.tools.javac.Main
import java.util.Scanner
import kotlin.system.exitProcess

// 4. 입력과 출력을 구현한다.
// 잘못된 입력에 대한 처리도 구현해주면 좋다.


// 다음은 Kotlin100_EX104를 보세요

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

class MainClass{
    var programState = ProgramState.PROGRAM_STATE_SHOW_AGG

    val scanner = Scanner(System.`in`)
    val studentManager = StudentManager(scanner)

    fun running(){
        while(true) {
            when (programState) {
                ProgramState.PROGRAM_STATE_INPUT_INFO -> {
                    // 사용자 점수를 입력받는 메서드 호출
                    studentManager.inputPoint()
                    // exitProcess(0)

                    // 다음 상태로 바꾼다.
                    programState = ProgramState.PROGRAM_STATE_SHOW_INFO
                }

                ProgramState.PROGRAM_STATE_SHOW_INFO -> {
                    // 학생들의 점수를 출력한다.
                    studentManager.printPoint()
                    // exitProcess(0)

                    // 다음 상태로 바꾼다
                    programState = ProgramState.PROGRAM_STATE_SHOW_AGG
                }

                ProgramState.PROGRAM_STATE_SHOW_AGG -> {
                    // 각 과목별 총점과 평균을 구한다.
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
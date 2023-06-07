import com.sun.tools.javac.Main
import java.util.Scanner
import kotlin.system.exitProcess

// 6. 각각의 상태별 메서드에 대해 필요한 기능들을 모두 구현해준다.


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
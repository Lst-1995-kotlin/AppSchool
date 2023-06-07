import com.sun.tools.javac.Main

// 2. 정의된 상태별로 분기한다
// 다음은 Kotlin100_EX102를 보세요

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 상태를 관리할 클래스
class MainClass{
    // 현재 상태
    // 프로그램의 초기 상태를 설정한다
    var programState = ProgramState.PROGRAM_STATE_INPUT_INFO

    fun running(){
        while(true) {
            // 상태별로 분기한다.
            when (programState) {
                ProgramState.PROGRAM_STATE_INPUT_INFO -> {

                }

                ProgramState.PROGRAM_STATE_SHOW_INFO -> {

                }

                ProgramState.PROGRAM_STATE_SHOW_AGG -> {

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
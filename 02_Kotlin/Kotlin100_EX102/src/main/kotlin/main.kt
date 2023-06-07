import com.sun.tools.javac.Main

// 3. 각 상태별 클래스를 정의한다.
// 다수의 상태에 대해 하나의 클래스로 정의해도 된다.
// StudentManager 클래스를 만들고 메서드를 정의한다.

// 다음은 Kotlin100_EX103를 보세요

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

class MainClass{
    var programState = ProgramState.PROGRAM_STATE_INPUT_INFO

    fun running(){
        while(true) {
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
// 1. 프로그램에 대한 각 상태를 enum 클래스로 정의한다.
// 다음은 Kotlin100_EX101 예제를 보세요

fun main(){

}

// 상태 정의
enum class ProgramState{
    // 학생의 정보를 입력받는 상태
    PROGRAM_STATE_INPUT_INFO,
    // 각 점수를 출력하는 상태
    PROGRAM_STATE_SHOW_INFO,
    // 과목별 총점과 평균을 출력하는 상태
    PROGRAM_STATE_SHOW_AGG
}
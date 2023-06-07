import java.util.Scanner

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass{

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programeState = ProgrameState.PROGRAM_STATE_ZOO_ADD_ANIMAL

    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        //프로그램 상태에 따른 분기
        while(true){
            when(programeState){
                // 동물원에 동물을 추가중인 상태
                ProgrameState.PROGRAM_STATE_ZOO_ADD_ANIMAL -> {
                    // 동물원 시작하는 함수
                    zooAddAnimal()
                    programeState = ProgrameState.PROGRAM_STATE_ZOO_PRINT_ANIMAL
                }
                // 현재 동물원에 있는 동물들의 상태를 출력하는 상태
                ProgrameState.PROGRAM_STATE_ZOO_PRINT_ANIMAL -> {
                    printZooAnimalAction()
                    programeState = ProgrameState.PROGRAM_STATE_POWER_DOWN
                }
                // 종료 여부
                ProgrameState.PROGRAM_STATE_POWER_DOWN ->{
                    if(reStartOrEnd()) break
                    programeState = ProgrameState.PROGRAM_STATE_ZOO_ADD_ANIMAL
                }
            }
        }

    }
}

// 프로그램 상태를 나타내는 enum
enum class ProgrameState{
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_ZOO_ADD_ANIMAL,
    PROGRAM_STATE_ZOO_PRINT_ANIMAL,
    PROGRAM_STATE_POWER_DOWN
}
























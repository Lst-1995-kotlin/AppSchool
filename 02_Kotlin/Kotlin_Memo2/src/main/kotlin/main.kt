import category.CategoryManagementClass
import category.CategorySelectClass
import memo.MemoAllShowClass
import java.util.Scanner
import kotlin.system.exitProcess

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass(){

    // 입력을 받는 스캐너는 모든 클래스에 들어가므로 생성자로 넣어준다.
    val scanner = Scanner(System.`in`)
    val loginClass = LoginClass(scanner)
    val mainMenuSelect = MainMenuSelect(scanner)
    val categoryManagementClass = CategoryManagementClass(scanner)
    val categorySelectClass = CategorySelectClass(scanner)
    val memoAllShowClass = MemoAllShowClass()

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programState = ProgramState.PROGRAM_STATE_LOGIN

    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        while(true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                ProgramState.PROGRAM_STATE_LOGIN -> {
                    val whether = loginClass.containsLogin()
                    if(whether){
                        // 로그인 정보가 있을 경우
                        loginClass.checkLogin()
                    } else {
                        // 로그인 정보가 없을 경우
                        loginClass.addLogin()
                    }
                    programState = ProgramState.PROGRAM_STATE_MAIN_MENU
                }

                ProgramState.PROGRAM_STATE_MAIN_MENU -> {
                    // 메인메뉴를 선택하여 해당 메뉴로 이동한다.
                    programState = mainMenuSelect.selectMenu()
                }

                ProgramState.PROGRAM_STATE_CATEGORY_MANAGEMENT -> {
                    // 카테고리 관리 메뉴로 이동한단.
                    categoryManagementClass.selectMenu()
                    programState = ProgramState.PROGRAM_STATE_MAIN_MENU
                }
                ProgramState.PROGRAM_STATE_CATEGORY_SELECT -> {
                    // 카테고리를 선택하는 메뉴로 이동한다.
                    categorySelectClass.selectCategory()

                    programState = ProgramState.PROGRAM_STATE_MAIN_MENU
                }
                ProgramState.PROGRAM_STATE_MEMO_ALL_SHOW -> {
                    // 현재 저장되어 있는 카테고리들의 메모를 모두 출력한다.
                    memoAllShowClass.printAllMemo()

                    programState = ProgramState.PROGRAM_STATE_MAIN_MENU
                }
                ProgramState.PROGRAM_STATE_EXIT -> {
                    println("프로그램을 종료합니다.")
                    exitProcess(0)
                }
            }
        }
    }
}

// 프로그램 상태를 나타내는 enum
enum class ProgramState{
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_LOGIN,
    PROGRAM_STATE_MAIN_MENU,
    PROGRAM_STATE_CATEGORY_MANAGEMENT,
    PROGRAM_STATE_CATEGORY_SELECT,
    PROGRAM_STATE_MEMO_ALL_SHOW,
    PROGRAM_STATE_EXIT
}
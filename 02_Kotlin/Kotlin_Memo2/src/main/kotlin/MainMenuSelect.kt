import java.util.Scanner

class MainMenuSelect(val scanner: Scanner) {

    fun selectMenu(): ProgramState{
        while(true){
            try{
                println("1. 메모 카테고리 관리")
                println("2. 메모 카테고리 선택")
                println("3. 메모 내용 전체 보기")
                println("4. 종료")
                print("메뉴를 선택해주세요 : ")
                val temp = scanner.nextLine()
                val select = temp.toInt()
                if(select !in 1 .. 4) throw Exception()
                return when(select){
                    1 -> ProgramState.PROGRAM_STATE_CATEGORY_MANAGEMENT
                    2 -> ProgramState.PROGRAM_STATE_CATEGORY_SELECT
                    3 -> ProgramState.PROGRAM_STATE_MEMO_ALL_SHOW
                    4 -> ProgramState.PROGRAM_STATE_EXIT
                    else -> ProgramState.PROGRAM_STATE_CATEGORY_SELECT
                }

            } catch (e: Exception){
                println("잘못 입력하였습니다.")
            }
        }

    }

}
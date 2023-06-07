import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.lang.NumberFormatException
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass{

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programState = ProgramState.PROGRAM_STATE_MENU_SELECT
    val health = Health()

    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        while(true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                ProgramState.PROGRAM_STATE_MENU_SELECT -> {
                    // 메뉴화면
                    programState = health.menuSelect()
                }

                ProgramState.PROGRAM_STATE_ADD_TODAY_HEALTH -> {
                    // 운동 추가 후 메뉴 선택으로 돌아감
                    programState = health.todayHealthSave()
                }
                ProgramState.PROGRAM_STATE_LOAD_HEALTH_STOREAGE -> {
                    // 날짜별 운동 기록
                    val check = health.historyHealthLoad()
                    if(check){
                        programState = health.printDayHealth()
                    } else {
                        programState =  ProgramState.PROGRAM_STATE_MENU_SELECT
                    }
                }
                ProgramState.PROGRAM_STATE_POWER_SHUTDOWN -> {
                    // 프로그램 종료
                    break
                }
            }
        }
    }
}

// 프로그램 상태를 나타내는 enum
enum class ProgramState(){
    PROGRAM_STATE_MENU_SELECT,
    PROGRAM_STATE_ADD_TODAY_HEALTH,
    PROGRAM_STATE_LOAD_HEALTH_STOREAGE,
    PROGRAM_STATE_POWER_SHUTDOWN
}

// 저장할 파일 명을 관리하는 enum
enum class FileName(val fileName :String){
    FILE_NAME("data1.dat")
}

// 운동에 관한 정보를 저장하는 데이터 클래스
data class HealthData(val kind: String, val count: Int, val set: Int, val date: String): Serializable{
    // 정보를 출력하는 함수
    fun printInfo(){
        println()
        println("운동 타입 : $kind")
        println("횟수 : $count")
        println("세트 : $set")
    }
}
class Health {
    val loadList = ArrayList<HealthData>()
    // 메뉴화면을 구성하고 메뉴 번호를 입력 받아 상태를 반환하는 함수
    // 1~3이 아닌 번호를 넣으면 다시 메뉴 호출함.
    fun menuSelect(): ProgramState {
        var select:Int
        while(true) {
            try {
                println("메뉴를 선택해주세요")
                println("1. 오늘의 운동기록")
                println("2. 날짜별 운동 기록 보기")
                println("3. 종료")
                val scanner = Scanner(System.`in`)
                print("번호 입력 : ")
                var temp = scanner.next()
                select = temp.toInt()
                if(select !in 1 .. 3) throw Exception()
                break
            } catch (e: Exception) {
                println("1 부터 3까지의 숫자만 입력바랍니다")
                println()
            }
        }

        val nextState = when (select) {
            1 -> ProgramState.PROGRAM_STATE_ADD_TODAY_HEALTH
            2 -> ProgramState.PROGRAM_STATE_LOAD_HEALTH_STOREAGE
            3 -> {
                println("프로그램을 종료 합니다.")
                ProgramState.PROGRAM_STATE_POWER_SHUTDOWN
            }
            else -> ProgramState.PROGRAM_STATE_MENU_SELECT
        }
        println()
        return nextState
    }


    // 오늘의 운동기록을 추가하는 함수
    fun todayHealthSave(): ProgramState {

        var kind : String
        var count : Int
        var set : Int

        while(true){
            try{
                val scanner = Scanner(System.`in`)
                print("운동 타입 : ")
                kind = scanner.nextLine()
                print("횟수 : ")
                var temp1 = scanner.next()
                count = temp1.toInt()
                print("세트 : ")
                var temp2 = scanner.next()
                set = temp2.toInt()
                break
            } catch (e: Exception){
                println("올바른 정수 값을 넣어 주세요")
            }
        }

        val date = LocalDate.now().toString()

        //val date2 = "2023-05-15"
        val fos = FileOutputStream(FileName.FILE_NAME.fileName, true)
        val oos = ObjectOutputStream(fos)

        oos.writeObject(HealthData(kind, count, set, date))

        oos.flush()
        oos.close()
        fos.close()


        return ProgramState.PROGRAM_STATE_MENU_SELECT
    }

    fun historyHealthLoad() : Boolean {
        loadList.clear()
        var fis : FileInputStream? = null
        try {
            fis = FileInputStream(FileName.FILE_NAME.fileName)
        }catch (e: Exception){
            println("읽어올 파일이 없습니다.")
            return false
        }

        if(fis != null){
            var ois: ObjectInputStream? = null
            while (true) {
                try {
                    ois = ObjectInputStream(fis)
                    loadList.add(ois.readObject() as HealthData)
                } catch (e: Exception) {
                    if (ois != null) {
                        ois.close()
                    }
                    break
                }
            }
        }
        return true
    }

    fun printDayHealth(): ProgramState {

        // 날짜 표시를 위해 중복 제거
        var dayList = mutableListOf<String>()
        // 가져온 정보 모두 넘김
        for (i in loadList) {
            dayList.add(i.date)
        }
        // 중복 날짜 제거
        dayList = dayList.distinct().sorted().toMutableList()

        var select : Int

        while(true){
            try {
                // 0을 입력 받으면 메뉴로 돌아간다.
                val scanner = Scanner(System.`in`)
                // 저장된 날짜 표시
                for (i in 0..dayList.size - 1) {
                    println("${i + 1} : ${dayList[i]}")
                }
                print("날짜를 선택해주세요(0. 이전) : ")
                var temp = scanner.next()
                select = temp.toInt()
                if(select !in 0 .. dayList.size) throw Exception()

                if (select == 0) {
                    println()
                    return ProgramState.PROGRAM_STATE_MENU_SELECT
                }

                break

            }catch (e: Exception){
                println("0 부터 ${dayList.size}번 내로 선택해주십시오.")
                println()
            }

        }

        // 리스트에 저장된 객체의 날짜와 선택한 날짜가 같다면
        // 객체의 정보를 출력한다.
        val selectDay = dayList[select - 1]
        println()
        println("${selectDay}의 운동 기록입니다.")
        for (i in loadList) {
            if (i.date == selectDay) {
                i.printInfo()
            }
        }
        println()

        return ProgramState.PROGRAM_STATE_MENU_SELECT
    }

}




















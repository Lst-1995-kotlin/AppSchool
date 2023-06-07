import java.io.File
import java.util.*

class ShowRecordClass(var scanner: Scanner, val mainClass: MainClass) {

    // 선택한 날짜 번호
    var selectedRecordNumber = 0

    // 기록을 볼 날짜를 선택하는 메서드
    fun selectRecordDay():Int{

        while(true) {
            try {
                println()
                // 등록된 날짜 목록을 출력한다.
                getRecordFileList()

                print("날짜를 선택해주세요(0. 이전) : ")

                val temp1 = scanner.next()
                val inputNumber = temp1.toInt()
                if(inputNumber !in 0..4){
                    println("잘못 입력하였습니다")
                } else {
                    return inputNumber
                }
            }catch(e:Exception){
                println("잘못 입력하였습니다.")
            }
        }
    }

    // 선택한 날짜의 운동 기록을 보여주는 메서드
    fun showSelectedRecord(){

        // 사용자가 선택한 번호의 파일 이름을 생성한다.
        val fileName = "${mainClass.recordFileList[selectedRecordNumber -1 ]}.record"
        // 선택한 날짜의 운동 기록 데이터를 불러온다.
        mainClass.getRecordData(fileName)


        println()
        println("${mainClass.recordFileList[selectedRecordNumber -1 ]}의 운동 기록입니다")

        for(recordClass in mainClass.recordList) {
            println()
            println("운동 타입 : ${recordClass.type}")
            println("횟수 : ${recordClass.count}")
            println("세트 : ${recordClass.set}")
        }

    }

    // 파일 목록을 불러온다.
    fun getRecordFileList(){
        // 파일 목록 리스트를 초기한다.
        mainClass.recordFileList.clear()

        // 현재 위치의 파일 목록을 가져온다.
        val dir = File(".")
        var fileList = dir.list()
        fileList = fileList.sortedArray()


        // 파일 목록에서 .record로 끝나는 것들만 담아 준다.
        for(temp1 in fileList){
            if(temp1.endsWith(".record")){
                val temp2 = temp1.replace(".record", "")
                mainClass.recordFileList.add(temp2)
            }
        }

        // 출력한다.
        if(mainClass.recordFileList.size == 0){
            println("등록된 운동 기록이 없습니다")
        } else {


            for (idx1 in 1..mainClass.recordFileList.size) {
                println("$idx1 : ${mainClass.recordFileList[idx1 - 1]}")
            }
        }
    }

}

enum class ShowRecordState{
    // 기록된 날짜를 선택하는 상태
    SHOW_RECORD_STATE_SELECT_DATE,
    // 선택된 날짜의 운동 기록을 보여주는 상태
    SHOW_RECORD_STATE_SHOW_RECORD
}
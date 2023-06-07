package memo

import fileInOut.LoadClass
import fileInOut.SaveClass
import java.util.Scanner

class MemoManagementClass(val scanner: Scanner) {
    fun selectMemoMenu(categoryName: String){ // 보고 싶은 카테고리의 번호를 매개 변수로 전달 받음.
        while(true){
            try {
                var memoList = LoadClass().loadData()[categoryName]
                if(memoList == null) memoList = ArrayList<MemoData>()
                if(memoList.isEmpty()){
                    println()
                    println("등록된 메모가 없습니다.")
                } else {
                    println()
                    for(i in 0 .. memoList.size-1){
                        println("${i + 1} : ${memoList[i].title}")
                    }
                }

                print("1. 메모보기, 2. 메모등록, 3. 메모수정, 4. 메모삭제, 5.이전 : ")
                val temp = scanner.nextLine()
                val selectMemoNum = temp.toInt()
                if(selectMemoNum !in 1 .. 5) throw Exception()

                when(selectMemoNum){
                    MemoState.MEMO_VIEW.num -> {
                        while(true) {
                            try {
                                println()
                                print("확인할 메모의 번호를 입력해주세요 (0. 이전) : ")
                                val tempmemoNum = scanner.nextLine()
                                val memoNum = tempmemoNum.toInt()
                                println()
                                if(memoNum !in 0 .. memoList.size) throw Exception()
                                if(memoNum == 0) break
                                println("제목 : ${memoList[memoNum-1].title}")
                                println("내용 : ${memoList[memoNum-1].contents}")
                                var exitCheck = false
                                try{
                                    print("이전으로 돌아가려면 0을 입력하세요 : ")
                                    val tempExitCheck = scanner.nextLine()
                                    val ExitCheck = tempExitCheck.toInt()
                                    if(ExitCheck == 0) exitCheck = true
                                } catch (e: Exception){
                                    println("잘못 입력하였습니다.")
                                }
                                if(exitCheck)break
                            }catch (e: Exception){
                                println("잘못 입력하였습니다.")
                            }
                        }
                    }

                    MemoState.MEMO_ADD.num -> {
                        print("메모 제목 : ")
                        val title = scanner.nextLine()
                        print("메모 내용 : ")
                        val contents = scanner.nextLine()
                        val newMemo = LoadClass().loadData()[categoryName]
                        if(newMemo != null){
                            newMemo.add(MemoData(title, contents))
                            SaveClass().saveCategory(categoryName,newMemo)
                        }
                    }

                    MemoState.MEMO_UPDATE.num -> {
                        while(true) {
                            try {
                                println()
                                print("수정할 메모의 번호를 입력해주세요 (0. 이전) : ")
                                val tempupdateMemoNum = scanner.nextLine()
                                val updateMemoNum = tempupdateMemoNum.toInt()
                                if(updateMemoNum !in 0 .. memoList.size) throw Exception()
                                if(updateMemoNum == 0) break

                                val updateMemo = LoadClass().loadData()[categoryName]

                                println("제목 : ${memoList[updateMemoNum-1].title}")
                                print("메모의 새로운 제목을 입력해주세요(0 입력시 무시합니다.) :")
                                var newTitle = scanner.nextLine()
                                if (newTitle != "0"){
                                    if(updateMemo != null){
                                        updateMemo[updateMemoNum-1].title = newTitle
                                    }
                                }

                                println("내용 : ${memoList[updateMemoNum-1].contents}")
                                print("메모의 새로운 내용을 입력해주세요(0 입력시 무시합니다.) :")
                                var newContents = scanner.nextLine()
                                if (newContents != "0"){
                                    if(updateMemo != null) {
                                        updateMemo[updateMemoNum - 1].contents = newContents
                                    }
                                }

                                if (updateMemo != null) {
                                    SaveClass().saveCategory(categoryName,updateMemo)
                                }

                                break

                            }catch (e: Exception){
                                print("잘못 입력하셨습니다.")
                            }
                        }
                    }

                    MemoState.MEMO_DELETE.num -> {
                        while(true){
                            try{
                                println()
                                print("삭제할 메모의 번호를 입력해주세요 (0. 이전) : ")
                                val tempdeleteNum = scanner.nextLine()
                                val deleteNum = tempdeleteNum.toInt()
                                if(deleteNum !in 0 .. memoList.size) throw Exception()
                                if(deleteNum == 0) break
                                val deleteMemo = LoadClass().loadData()[categoryName]
                                if(deleteMemo != null){
                                    deleteMemo.removeAt(deleteNum-1)
                                    SaveClass().saveCategory(categoryName,deleteMemo)
                                }
                                break

                            }catch (e: Exception){
                                println("잘못 입력하셨습니다.")
                            }
                        }
                    }
                    MemoState.MEMO_MENU_BACK.num -> {
                        break
                    }
                }
            }catch (e: Exception){
                println("잘못 입력하셨습니다.")
            }

        }

    }
}

enum class MemoState(val num:Int){
    MEMO_VIEW(1),
    MEMO_ADD(2),
    MEMO_UPDATE(3),
    MEMO_DELETE(4),
    MEMO_MENU_BACK(5)

}
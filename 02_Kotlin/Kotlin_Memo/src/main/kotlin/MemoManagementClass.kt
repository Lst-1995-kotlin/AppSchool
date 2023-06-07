import java.util.Scanner

class MemoManagementClass(val scanner: Scanner) {
    fun selectMemoMenu(categoryName: String){ // 보고 싶은 카테고리의 번호를 매개 변수로 전달 받음.
        while(true){
            try {
                val memoList = LoadClass().loadCategoryMemo(categoryName)
                if(memoList.isEmpty()){
                    println()
                    println("등록된 메모가 없습니다.")
                } else {
                    println()
                    for(i in 0 .. memoList.size-1){
                        println("${i+1} : ${memoList[i].title}")
                    }
                }

                println()
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
                        val memoList = ArrayList<Memo>()
                        memoList.add(Memo(title, contents))
                        SaveClass().saveCategory(categoryName,memoList)
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

                                println("제목 : ${memoList[updateMemoNum-1].title}")
                                print("메모의 새로운 제목을 입력해주세요(0 입력시 무시합니다.) :")
                                var newTitle = scanner.nextLine()
                                if (newTitle != "0") memoList[updateMemoNum-1].title = newTitle

                                println("내용 : ${memoList[updateMemoNum-1].contents}")
                                print("메모의 새로운 내용을 입력해주세요(0 입력시 무시합니다.) :")
                                var newContents = scanner.nextLine()
                                if (newContents != "0") memoList[updateMemoNum-1].contents = newContents
                                UpdateClass().catagoryMemoUpdate(categoryName,memoList)

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

                                memoList.removeAt(deleteNum-1)
                                UpdateClass().catagoryMemoUpdate(categoryName,memoList)
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
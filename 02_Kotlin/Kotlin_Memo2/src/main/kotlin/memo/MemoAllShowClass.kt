package memo

import fileInOut.LoadClass

class MemoAllShowClass {

    fun printAllMemo(){
        // 현재 저장되어있는 카테고리 이름 리스트를 가지고 온다.
        val categoryMap = LoadClass().loadData()

        for(category in categoryMap){
            println("---------------------------------------")
            println(category.key)
            println("---------------------------------------")
            val memoList = category.value
            if(memoList.isEmpty()) {
                println("등록된 메모가 없습니다.")
                println()
            }
            for(memo in memoList){
                memo.printMemo()
            }
        }
    }


}
class MemoAllShowClass {

    fun printAllMemo(){
        // 현재 저장되어있는 카테고리 이름 리스트를 가지고 온다.
        val categoryList = LoadClass().loadCategoryName()

        for(category in categoryList){
            println("---------------------------------------")
            println(category.split(".")[0])
            println("---------------------------------------")
            val memoList = LoadClass().loadCategoryMemo(category)
            if(memoList.isEmpty()) println("등록된 메모가 없습니다.")
            for(memo in memoList){
                println()
                memo.printMemo()
            }
            println()
        }
    }


}
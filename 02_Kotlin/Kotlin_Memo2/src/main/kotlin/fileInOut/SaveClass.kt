package fileInOut

import memo.MemoData

class SaveClass : WriteClass() {

    // 전달받은 이름의 카테고리에 메모를 저장한다.
    fun saveCategory(name:String,memoList: ArrayList<MemoData>){
        var dataMap = LoadClass().loadData()
        dataMap[name] = memoList

        writeFile(dataMap)
    }

}
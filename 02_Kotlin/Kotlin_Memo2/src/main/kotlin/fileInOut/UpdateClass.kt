package fileInOut

import memo.MemoData

class UpdateClass : WriteClass() {
    // 카테고리의 이름을 수정하여 새로 저장한다.
    fun updateCategoryName(oriName:String, changeName:String){
        val origin = LoadClass().loadData()
        val newData = LinkedHashMap<String,ArrayList<MemoData>>()

        for(i in origin){
            if(i.key == oriName){
                newData[changeName] = i.value
            } else {
                newData[i.key] = i.value
            }
        }

        writeFile(newData)

    }
}
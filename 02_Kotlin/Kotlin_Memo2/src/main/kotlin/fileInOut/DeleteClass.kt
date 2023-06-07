package fileInOut

class DeleteClass : WriteClass() {
    // 전달 받은 이름의 카테고리를 삭제한다.
    fun deleteCategory(name:String){
        var dataMap = LoadClass().loadData()
        dataMap.remove(name)
        writeFile(dataMap)
    }

}
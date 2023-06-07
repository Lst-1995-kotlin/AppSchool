class UpdateClass {

    // 카테고리 이름 업데이트 함수
    fun catagoryNameUpdate(origin : String, new: String){

        val oriList = LoadClass().loadCategoryMemo(origin)

        if(oriList.isEmpty()){
            SaveClass().addCategory(new)
        } else {
            SaveClass().saveCategory(new,LoadClass().loadCategoryMemo(origin))
        }
        DeleteClass().deleteCategory(origin)
    }

    // 카테고리 내 메모 업데이트 함수
    fun catagoryMemoUpdate(origin : String, list: ArrayList<Memo>){
        DeleteClass().deleteCategory(origin)
        SaveClass().saveCategory(origin,list)
    }

}
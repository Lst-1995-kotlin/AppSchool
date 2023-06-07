import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class LoadClass {

    // 저장된 카테고리들의 이름을 가져온다.
    fun loadCategoryName(): List<String> {
        if(File(".").list() != null){
            return File(".").list().filter{it.endsWith(".category")}
        }
        return emptyList()
    }

    // 전달받은 이름을 가진 카테고리 내 정보를 반환한다.
    fun loadCategoryMemo(name:String):ArrayList<Memo>{
        var fis = FileInputStream(name)
        var ois : ObjectInputStream? = null
        var memoList = ArrayList<Memo>()
        while(true){
            try {
                ois = ObjectInputStream(fis)
                memoList.add(ois.readObject() as Memo)
            }catch (e : Exception){
                if(ois != null){
                    ois.close()
                }
                break
            }
        }
        fis.close()
        return memoList
    }


}
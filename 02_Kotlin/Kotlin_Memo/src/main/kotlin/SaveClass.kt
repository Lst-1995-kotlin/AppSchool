import java.io.FileOutputStream
import java.io.ObjectOutputStream

class SaveClass {

    fun addCategory(name: String){
        val fos = FileOutputStream(name)
        fos.close()
    }

    // 전달받은 이름의 카테고리에 메모를 저장한다.
    fun saveCategory(name:String,memoList: ArrayList<Memo>){
        var fos = FileOutputStream(name,true)
        var oos : ObjectOutputStream? = null
        for(memo in memoList){
            oos = ObjectOutputStream(fos)
            oos.writeObject(memo)
        }
        if(oos != null){
            oos.flush()
            oos.close()
        }
        fos.close()
    }

}
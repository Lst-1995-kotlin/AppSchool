package fileInOut

import memo.MemoData
import java.io.FileInputStream
import java.io.ObjectInputStream

class LoadClass {

    // 파일에 저장된 맵을 반환한다.
    fun loadData():LinkedHashMap<String, ArrayList<MemoData>>{

        var dataMap = LinkedHashMap<String,ArrayList<MemoData>>()
        try {
            var fis = FileInputStream("Info.dat")

            var ois = ObjectInputStream(fis)
            dataMap = ois.readObject() as LinkedHashMap<String, ArrayList<MemoData>>

            ois.close()
            fis.close()

        }catch (e : Exception){
            //println("데이터가 존재하지 않거나 없음.")
        }

        return dataMap
    }


}
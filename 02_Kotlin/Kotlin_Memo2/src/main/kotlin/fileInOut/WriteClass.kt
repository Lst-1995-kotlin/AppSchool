package fileInOut

import memo.MemoData
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

open class WriteClass {
    protected fun writeFile(writeMap : LinkedHashMap<String, ArrayList<MemoData>>){
        File("Info.dat").delete()
        var fos = FileOutputStream("Info.dat",true)
        var oos = ObjectOutputStream(fos)

        oos.writeObject(writeMap)

        oos.flush()
        oos.close()
        fos.close()
    }
}
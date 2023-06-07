package memo

import java.io.Serializable

data class MemoData(var title:String, var contents: String):Serializable{
    fun printMemo(){
        println()
        println("제목 : $title")
        println("내용 : $contents")
        println()
    }
}

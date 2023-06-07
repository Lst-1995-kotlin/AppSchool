import java.io.Serializable

data class Memo(var title:String, var contents: String):Serializable{
    fun printMemo(){
        println("제목 : $title")
        println("내용 : $contents")
    }
}

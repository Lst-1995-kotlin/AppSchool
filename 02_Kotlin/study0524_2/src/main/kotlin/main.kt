import java.io.*
import java.math.BigInteger
import java.util.Scanner

enum class Name(val fileName: String){
    FILE_NAME_STUDY("test4.txt")
}

fun main(){
    val scan = Scanner(System.`in`)
    println("추가되는 자료는 TC(count,200) 뿐 입니다.")
    // 잘 저장하고 읽어오는지 확인하기 위해
    var count = 1
    var selectNum = 0
    while(true){
        print("생성하여 저장하시겠습니까? 1.진행,2.중단 후 저장파일 확인 : ")
        selectNum = scan.nextInt()
        if(selectNum != 1) {
            fileLoad()
            continue
        }
        val oc = TC(count,200)
        count++
        fileSave(oc)
    }


}


// 파일에 작성하는 함수
fun fileSave(save:TC){

    val fos = FileOutputStream(Name.FILE_NAME_STUDY.fileName,true)
    val dos = ObjectOutputStream(fos)

    dos.writeObject(save)

    dos.close()
    fos.close()

    println("쓰기 완료")
}

// 파일에서 읽어오는 함수
fun fileLoad(){
    val result = ArrayList<TC>()
    val fis = FileInputStream((Name.FILE_NAME_STUDY.fileName))
    var dis: ObjectInputStream? = null
    while(true){
        try{
            dis = ObjectInputStream(fis)
            val readObj = dis.readObject() as TC
            result.add(readObj)


        } catch (e: Exception){
            if (dis != null) {
                dis.close()
            }
            fis.close()
            break
        }
    }
    for(i in result) i.printSum()

}


// 객체 직렬화를 한 클래스
class TC(var a1:Int, var a2: Int ) : Serializable {
    fun printSum(){
        println("a1: ${a1}, a2: ${a2}, a1 + a2 = ${a1 + a2}")
    }
}

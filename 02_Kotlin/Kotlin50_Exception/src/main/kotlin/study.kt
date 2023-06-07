fun main(){
    // try ~ catch
    try{
        val stringNum = "문자열을 숫자로 변환해보자"
        stringNum.toInt()
    } catch (e: Exception){
        println("장애 발생 시 실행됨.")
        println("e.printStackTrace() : ${e.printStackTrace()}")
    } finally {
        println("장애가 발생하든 안하든 반드시 실행해야 되는 기능이 있을 경우")
    }

    println("try ~ catch 문 이후에 작성한 코드는 문제 없이 실행된다.")
}
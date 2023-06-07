import java.lang.Math.random
import kotlin.concurrent.thread

fun main(){
    // 자바와 동일하게 상속을 받거나 인터페이스를 구현하여 사용하는 경우
    val t1 = Test1().start()
    val t2 = Thread(Test2())
    t2.start()

    // thread 고차 함수 사용
    // run 메서드에 들어갈 코드(쓰래드로 처리될 코드)
    // 익명함수나 람다식으로 작성해서 넣어주면 해당 코드는 쓰래드로 운영해준다.
    // start() 도 자동으로 호출하기 때문에 개발자가 명시적으로 호출하지 않아도 된다.
    thread {
        for(i in 1 .. 10){
            println("thread 고차 함수 :$i ")
        }
    }

}

class Test1 : Thread(){
    override fun run() {
        super.run()
        for(i in 1 .. 10){
            print("Test1 : $i ")
        }
    }
}

class Test2 : Runnable{
    override fun run() {
        for( i in 1 .. 10){
            println("Test2 : $i")
        }
    }
}

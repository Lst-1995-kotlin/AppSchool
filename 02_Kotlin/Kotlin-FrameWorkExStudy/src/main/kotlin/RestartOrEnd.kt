import java.util.Scanner

fun reStartOrEnd() : Boolean {
    val scan = Scanner(System.`in`)
    print("프로그램을 종료 하시겠습니까? 1.예, 2.아니오 :")
    val state = scan.nextInt()
    return state == 1
}
import java.lang.NumberFormatException
import java.util.Scanner

// 깃 한번에 커밋 및 푸시 테스트


// 플레이어가 입력한 위치에 대한 정보를 저장할 이차원 배열
val board = Array(3) { Array<String>(3, {" "}) }

// 현재 토큰을 놔야하는 플레이어
var nowPlayer = Player.PLAYER1

// 플레이어 별 토큰 저장
enum class Player(val token:String){
    PLAYER1("O"),
    PLAYER2("X")
}

// 변경한 Exception
class CustomException : Exception()

fun main(){
    while (true){
        // 게임판수를 출력하는 기능
        printCount()
        // 게임판을 출력하는 기능
        printTiTekToBoard()
        //println("printTiTekToBoard() 작동 : ${count++}")
        // 플레이어가 입력하는 정보를 받는 기능
        var input = playInfo()
        //println("var input = playInfo() 작동 : ${count++}")
        // 플레이어가 입력한 위치에 o,x 를 저장하는 기능
        inputSave(input)
        //println("inputSave(input) 작동 : ${count++}")
        // 플레이어가 우승하였는지 확인하는 기능
        // true : 우승, false : 패배
        //println("winCheck() 작동 : ${count++}")
        if(winCheck(input[0],input[1])){
            break
        }

        // 플레이어 변경
        //println("changePlayer() 작동 : ${count++}")
        changePlayer()

    }
    printTiTekToBoard()
    println("${nowPlayer.name} 승리!")

}
var count = 1
fun printCount(){
    println("${count}번째 턴")
    count++
}

// 플레이어가 입력하는 정보를 받는 기능
fun playInfo(): IntArray{
    val scanner = Scanner(System.`in`)
    var row = 5
    var col = 5
    while(true){
        try{
            print("${nowPlayer.name} 입력(줄,칸) : ")
            val input = scanner.next()
            row = input.split(",")[0].toInt()
            col = input.split(",")[1].toInt()

            // 허용 범위 내인지 확인
            if(row !in 0 .. 2 || col !in 0 .. 2){
                throw NumberFormatException()
            }
            // 이미 놓여진 곳에 놓으려는지 확인.
            if(board[row][col] != " "){
                throw CustomException()
            }
            // 에러가 없다면 while문 종료
            break
        }catch (e : CustomException){
            println("이미 놓여진 곳에는 말을 넣을 수 없습니다")
        }catch (e : NumberFormatException){
            println("줄과 칸의 입력 허용 범위는 0 ~ 2 까지 입니다.")
        }catch (e : Exception){
            println("1,2 혹은 2,0 등 올바른 형식으로 입력해주십시오.")
        }
    }
    return intArrayOf(row, col)
}

// 플레이어가 입력한 위치에 o,x 를 저장하는 기능
fun inputSave(info : IntArray){
    val row = info[0]
    val col = info[1]
    board[row][col] = nowPlayer.token
}


val rowArray = intArrayOf(-1,-1,-1,0,0,1,1,1)
val colArray = intArrayOf(-1,0,1,-1,1,-1,0,1)
// 해당 플레이어가 턴을 종료하였을 때 우승하였는지 체크하는 기능
// 기준점은 마지막에 놓은 위치로 한다.
// 우승이라면 true, 아니라면 false를 반환한다.
fun winCheck(row:Int,col:Int): Boolean{
    var count : Int
    for(i in 0 .. 7){
        var nextRow = row
        var nextCol = col
        count = 1
        while(true){
            nextRow += rowArray[i]
            nextCol += colArray[i]

            if(nextRow < 0 || nextCol < 0 || nextRow >= board.size || nextCol >= board[0].size) break
            if(board[nextRow][nextCol] != nowPlayer.token) break
            if(board[nextRow][nextCol] == nowPlayer.token) count++
            if(count == 3) break
        }
        if(count == 3) return true
    }
    return false
}

// 현재 플레이어를 바꾸는 기능
fun changePlayer(){
    if(nowPlayer == Player.PLAYER1){
        nowPlayer = Player.PLAYER2
    } else {
        nowPlayer = Player.PLAYER1
    }
}


// 틱택토 게임 판 출력하는 기능

fun printTiTekToBoard(){
    println("  0 1 2")
    for(i in 0 .. board.size-1){
        print("$i ")
        for(j in 0 .. board.size-1){
            print(board[i][j])
            if(j != board.size-1) print("|")
            else println()
        }
        if(i != 2) println("  -+-+-")
    }
    println()

}

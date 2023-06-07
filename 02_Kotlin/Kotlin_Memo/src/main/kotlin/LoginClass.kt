import java.io.*
import java.util.Scanner

enum class Login(name: String){
    LOGIN_FILE("Login.dat")
}

class LoginClass(var scanner: Scanner) {
    //로그인 정보가 있는지 확인
    fun containsLogin():Boolean{
        var fis : FileInputStream? = null
        try{
            fis = FileInputStream(Login.LOGIN_FILE.name)
        } catch (e: Exception){
            if(fis != null) fis.close()
            return false
        }
        return true
    }
    // 로그인 정보를 추가한다.
    fun addLogin(){
        while(true){
            try {
                println()
                print("설정할 비밀번호를 입력해주세요 : ")
                val temp = scanner.nextLine()
                val password = temp.toInt()
                print("한번 더 입력해주세요 : ")
                val temp2 = scanner.nextLine()
                val password2 = temp2.toInt()
                if(password != password2) throw Exception()

                val fos = FileOutputStream(Login.LOGIN_FILE.name)
                val dos = DataOutputStream(fos)

                dos.writeInt(password)

                dos.flush()
                dos.close()
                fos.close()
                break
            } catch (e: Exception){
                println("다시 입력해주세요")
            }
        }
    }
    // 패스워드를 입력 받아 확인한다.
    fun checkLogin(){
        while(true){
            try {
                println()
                print("로그인 하시려면 비밀 번호를 입력하세요 : ")
                val temp = scanner.nextLine()
                val password = temp.toInt()
                val fis = FileInputStream(Login.LOGIN_FILE.name)
                val dis = DataInputStream(fis)
                val loadPassword = dis.readInt()

                if(password != loadPassword) throw Exception()
                break
            } catch (e : Exception){
                print("잘못 입력하였습니다.")
            }

        }

    }

}

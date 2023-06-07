import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

fun main(){
    // saveDataStream()
    // readDataStream()
    // saveObjectStream()
    // readObjectStream()
}

class TestClass1(var a1:Int, var a2:Int) : Serializable{

    fun printValue(){
        println("a1 : $a1")
        println("a2 : $a2")
    }
}

// DataStream으로 쓰기
fun saveDataStream(){
    val fos = FileOutputStream("data1.dat")
    val dos = DataOutputStream(fos)
    
    dos.writeInt(100)
    dos.writeDouble(11.11)
    dos.writeUTF("문자열1")

    dos.flush()
    dos.close()
    fos.close()
    
    println("쓰기 완료")
}

// DataStream으로 읽기
fun readDataStream(){
    val fis = FileInputStream("data1.dat")
    val dis = DataInputStream(fis)

    val a1 = dis.readInt()
    val a2 = dis.readDouble()
    val a3 = dis.readUTF()

    dis.close()
    fis.close()

    println("a1 : $a1")
    println("a2 : $a2")
    println("a3 : $a3")
}

// ObjectStream으로 쓰기
fun saveObjectStream(){
    val fos = FileOutputStream("data2.dat")
    val oos = ObjectOutputStream(fos)
    
    val t1 = TestClass1(100, 200)
    val t2 = TestClass1(1000, 2000)
    
    oos.writeObject(t1)
    oos.writeObject(t2)

    oos.flush()
    oos.close()
    fos.close()
    println("쓰기 완료")
}

fun readObjectStream(){
    val fis = FileInputStream("data2.dat")
    val ois = ObjectInputStream(fis)

    val t3 = ois.readObject() as TestClass1
    val t4 = ois.readObject() as TestClass1

    ois.close()
    fis.close()

    t3.printValue()
    t4.printValue()
}



fun main(){

}

//     8. 부모 클래스들이 상속 받거나 구현해야 하는 인터페이스들을 설정해준다.
//     8-1. 만약 자식 클래스가 구현해야 하는 메서드가 있다면 추상 메서드로 정의하고 클래스는 추상 클래스로 정의한다.


// 동물원 클래스
class Zoo{
    // 동물들을 저장할 변수
    val animalList = ArrayList<Animal>()

    // 추가 할 동물을 입력 받는 기능
    fun addInputAnimal(){

    }
    // 추가된 동물들의 정보를 출력하는 기능
    fun printAnimalInfo(){

    }
    // 추가된 동물들을 사육장에 넣는 기능
    fun animalGoVivarium(){

    }
    // 추가된 동물들을 해양생물 놀이터에 넣는 기능
    fun animalGoMarineLifePlayGround(){

    }
    // 추가된 동물들을 방목장에 넣는 기능
    fun animalGoPasture(){

    }
    // 추가된 동물들을 밀림에 넣는 기능
    fun animalGoJungle(){

    }
}
// 동물 사육장 클래스
class Vivarium{
    // 동물들이 밥을 먹는 기능
    fun animalEat(){

    }
}
// 해양생물놀이터 클래스
class MarineLifePlayGround{
    // 동물들이 헤엄을 치는 기능
    fun animalSwimming(){

    }
}
// 방목장 클래스
class Pasture{
    // 동물들이 뛰어다니는 기능
    fun animalRun(){

    }
}
// 밀림 클래스
class Jungle{
    // 동물들이 사냥을 하는 기능
    fun animalHunt(){

    }
}

// 동물 클래스
open abstract class Animal(var type: String, var size: String):Eat{
    abstract fun printInfo()

}

// 돌고래 클래스
class Dolphin(type: String,size: String, swimmingSpeed: Int) : Animal(type, size),Swim {
    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override var swimSpeed = swimmingSpeed

    override fun swimming() {
        TODO("Not yet implemented")
    }
}

// 상어 클래스
class Shark(type: String,size: String, swimmingSpeed: Int) : Animal(type, size),Swim{
    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override var swimSpeed: Int = swimmingSpeed

    override fun swimming() {
        TODO("Not yet implemented")
    }

}
// 말 클래스
class Horse(type: String, size: String, runSpeed : Int) : Animal(type, size),Run2{
    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override var runSpeed = runSpeed

    override fun run() {
        TODO("Not yet implemented")
    }


}
// 기린 클래스
class Giraffe(type: String, size: String, runSpeed: Int) : Animal(type, size),Run2,Hunt{
    override var runSpeed = runSpeed

    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }


    override fun run() {
        TODO("Not yet implemented")
    }

    override fun hunt() {
        TODO("Not yet implemented")
    }

}
// 호랑이 클래스
class Tiger(type: String, size: String) : Animal(type, size),Run{

    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun run() {
        TODO("Not yet implemented")
    }

}
// 사자 클래스
class Lion(type: String, size: String,feeVolume:Int) : Animal(type, size),Hunt{

    //var feeVolume = 0

    override fun printInfo() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun hunt() {
        TODO("Not yet implemented")
    }
}

// 하기 4개는 메서드로 만든다는 조건이 있음
// 밥을 먹는 메서드를 가진 클래스
interface Eat{
    // 먹는 양
    var feeVolume:Int

    // 밥을 먹는 기능
    fun eat()
}
// 헤엄을 치는 메서드를 가진 클래스
interface Swim{
    // 헤엄 치는 속도
    var swimSpeed: Int
    // 헤엄을 치는 기능
    fun swimming()
}
// 뛰어다니는 메서드를 가진 클래스
interface Run{
    // 뛰어 다니는 기능
    fun run()
}
// Run 과 Run2는 뛰어 다니는 기능이 겹치므로 Run2에 Run을 구현한다
interface Run2 : Run{
    // 달리기 속도를 가지는 변수
    var runSpeed: Int

    // 뛰어 다니는 기능
    override fun run()
}

// 사냥을 하는 메서드를 가진 클래스
interface Hunt{
    // 사냥하는 기능
    fun hunt()
}

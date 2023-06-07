import java.util.Scanner

fun main(){
    val z1 = Zoo()
    z1.addAnimal()
    z1.printInfo()
    z1.inAnimalFarm()
    z1.inMarineLifePlayground()
    z1.inPasture()
    z1.inJungle()
}
//9. 최종 자식 클래스에 상속과 인터페이스를 설정해준다.


// 동물원 클래스
class Zoo{
    // 변수
    // 동물들을 저장할 리스트
    val animalList = ArrayList<Animal>()

    // 사용자의 입력을 받을 스캐너
    val scanner = Scanner(System.`in`)

    // 메서드
    // 추가할 동물들을 입력받는 메서드
    fun addAnimal(){
        while(true){
            println("어떤 동물을 동물원에 넣어줄까요?")
            print("1. 돌고래, 2. 상어, 3. 말, 4. 기린, 5. 호랑이, 6. 사자, 0. 그만넣어 :")
            val typeNumber = scanner.nextInt()
            if(typeNumber !in 0 .. 6){
                println("잘못 입력하셨습니다.")
                continue
            }
            if(typeNumber == 0) break
            when(typeNumber){
                1 -> animalList.add(Dolphin("돌고래","돌고래 만큼 크다"))
                2 -> animalList.add(Shark("상어","상어 만큼 크다"))
                3 -> animalList.add(Horse("말", "말 만큼 크다"))
                4 -> animalList.add(Giraffe("기린","기린 만큼 크다"))
                5 -> animalList.add(Tiger("호랑이", "호랑이 만큼 크다"))
                6 -> animalList.add(Lion("사자", "사자만큼 크다"))
                else -> animalList.add(Lion("123", "123"))
            }
        }
    }
    // 저장된 동물들의 정보를 출력하는 메서드
    fun printInfo(){
        for(animal in animalList){
            animal.printInfo()
            println()
        }

    }
    // 동물 사육장에 넣어주는 메서드
    fun inAnimalFarm(){
        for(animal in animalList){
            animal.animalEat()
        }
        println()
    }
    // 해양 생물 놀이터에 넣어주는 메서드
    fun inMarineLifePlayground(){
        for(animal in animalList){
            animal.animalSwimming()
        }
        println()
    }
    // 방목장에 넣어주는 메서드
    fun inPasture(){
        for(animal in animalList){
            animal.animalRun()
        }
        println()
    }
    // 밀림에 넣어주는 메서드
    fun inJungle(){
        for(animal in animalList){
            animal.animalHunt()
        }
        println()
    }


}
// 추가
// 동물들을 모두 담을 수 있는 클래스 생성
// 동물들을 저장할 리스트 선언하며 필요성 느껴 생성.
// 동물 클래스 중 중복되는 내용 정리
abstract open class Animal(var type:String, var size:String):AnimalFarm,MarineLifePlayground,Pasture,Jungle{
    abstract fun printInfo()
}


// 동물사육장 인터페이스
interface AnimalFarm{
    // 메서드
    // 동물들이 밥을 먹는다
    fun animalEat()


}
// 해양생물놀이터 인터페이스
interface MarineLifePlayground{
    // 메서드
    // 돌고래와 상어가 헤엄친다.
    fun animalSwimming()
}
// 방목장 인터페이스
interface Pasture{
    // 메서드
    // 말과 기린 호랑이가 뛰어다닌다.
    fun animalRun()
}
// 밀림 인터페이스
interface Jungle{
    // 메서드
    // 상어와 기린과 사자가 사냥을 한다.
    fun animalHunt()
}
// 돌고래 인터페이스
class Dolphin(type: String, size: String): Animal(type, size){
    val swimmingSpeed = 300
    // 메서드
    // 자신의 정보를 출력한다.
    override fun printInfo(){
        println("타입 : $type")
        println("크기 : $size")
        println("헤엄 속도 : ${swimmingSpeed}노트")
    }
    // 밥을 먹는다.
    override fun animalEat() {
        println("${type}은 냠냠냠 하고 먹습니다.")
    }

    override fun animalSwimming() {
        println("${type}은 돌~돌~ 하면서 헤엄칩니다.")
    }

    override fun animalRun() {
        return
    }

    override fun animalHunt() {
        return
    }


}
// 상어클래스
class Shark(type: String, size: String): Animal(type, size){
    // 변수
    // 타입, 크기, 헤엄속도
    val swimmingSpeed = 500
    override fun animalEat() {
        println("${type}은 얌얌얌하며 먹습니다.")
    }

    override fun animalSwimming() {
        println("${type}은 슈웅 슈웅하며 헤엄 칩니다.")
    }

    override fun animalRun() {
        return
    }

    override fun animalHunt() {
        println("${type}은 으아아아아아!!! 하면서 사냥합니다.")
    }

    // 메서드
    // 자신의 정보를 출력한다.
    override fun printInfo(){
        println("타입 :$type")
        println("크기 : $size")
        println("헤엄 속도 : ${swimmingSpeed}노트")
    }

}
// 말 클래스
class Horse(type: String, size: String) : Animal(type, size) {

    val runSpeed = 300
    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

    override fun animalEat() {
        println("${type}은 당근 당근하며 먹습니다.")
    }

    override fun animalSwimming() {
        return
    }

    override fun animalRun() {
        println("${type}은 이히히히힝~ 하며 달립니다.")
    }

    override fun animalHunt() {
        return
    }


}
// 기린 클래스
class Giraffe(type: String, size: String) : Animal(type, size) {
    val runSpeed = 500
    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

    override fun animalEat() {
        println("${type}은 풀풀풀 하며 먹습니다.")
    }

    override fun animalSwimming() {
        return
    }

    override fun animalRun() {
        println("${type}은 영차~ 영차~하며 뜁니다.")
    }

    override fun animalHunt() {
        println("${type}은 가즈아~하며 사냥합니다.")
    }



}
// 호랑이 클래스
class Tiger(type: String, size: String) : Animal(type, size) {

    val feedingVolume = 500
    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("먹이량 : ${feedingVolume}km/h")
    }

    override fun animalEat() {
        println("${type}은 아작 아작하며 먹습니다.")
    }

    override fun animalSwimming() {
        return
    }

    override fun animalRun() {
        println("${type}은 헥~ 헥~하며 뜁니다.")
    }

    override fun animalHunt() {
        return
    }


}
// 사자 클래스
class Lion(type: String, size: String) : Animal(type, size) {

    val feedingVolume = 600
    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("먹이량 : ${feedingVolume}km/h")
    }

    override fun animalEat() {
        println("${type}은 꿀꺽 꿀꺽하며 먹습니다.")
    }

    override fun animalSwimming() {
        return
    }

    override fun animalRun() {
        return
    }

    override fun animalHunt() {
        println("${type}은 암컷아 사냥해와라~하며 사냥합니다.")
    }


}
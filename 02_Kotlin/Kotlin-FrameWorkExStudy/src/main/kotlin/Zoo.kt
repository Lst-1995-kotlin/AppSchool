import java.util.Scanner

lateinit var zoo : Zoo

// 추가된 동물들을 저장하는 리스트
val animalList = ArrayList<Animal>()

// 동물원에 동물들을 추가하는 함수
internal fun zooAddAnimal(){
    zoo = Zoo()
    zoo.animalAdd()
}

// 동물원에 있는 동물들의 정보와 행동들을 출력
internal fun printZooAnimalAction(){
    // 동물들의 정보를 출력
    for(animal in animalList){
        animal.printInfo()
    }
    println()
    for(animal in animalList){
        animal.eat()
    }
    println()
    for(animal in animalList){
        if(animal is Marine){
            animal.swimming()
        }
    }
    println()
    for(animal in animalList){
        if(animal is Run){
            animal.running()
        }
    }
    println()
    for(animal in animalList){
        if(animal is Hunt){
            animal.hunt()
        }
    }
    println()
}

// 동물원 클래스
class Zoo{

    // 동물을 추가 하는 함수
    fun animalAdd(){
        val scan = Scanner(System.`in`)

        while(true){
            println("어떤 동물을 동물원에 넣어줄까요?")
            print("1. 돌고래, 2. 상어, 3. 말, 4. 기린, 5. 호랑이, 6. 사자, 0. 그만넣어 : ")
            val inputNumber = scan.nextInt()
            if(inputNumber !in 0 .. 6){
                println("0 부터 6 까지의 숫자만 입력 가능합니다.")
                continue
            }
            if(inputNumber == 0) break

            when(inputNumber){
                1 -> animalList.add(Dolphin("돌고래","돌고래 만큼 크다", 300))
                2 -> animalList.add(Shark("상어","상어 만큼 크다", 500))
                3 -> animalList.add(Horse("말", "말 만큼 크다", 300))
                4 -> animalList.add(Giraffe("기린","기린 만큼 크다",500))
                5 -> animalList.add(Tiger("호랑이", "호랑이 만큼 크다", 500))
                6 -> animalList.add(Lion("사자", "사자만큼 크다", 600))
                else -> animalList.add(Lion("123", "123", 600))
            }
        }

    }
}


// 동물 클래스
open abstract class Animal(var type:String, var size: String){
    // 먹는 기능은 모두 가지고 있다.
    abstract fun eat()

    abstract fun printInfo()

}

class Dolphin(type: String, size: String, swimSpeed: Int):Marine(type, size, swimSpeed){
    override fun eat() {
        println("${type}는 밥을 먹을 때 냠냠냠 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("헤엄속도 : ${swimSpeed}노트")
    }

    override fun swimming() {
        println("${type}는 헤엄을 칠 때 돌~돌~ 하면서 헤엄을 친다.")
    }

}

class Shark(type: String, size: String, swimSpeed: Int):Marine(type, size, swimSpeed),Hunt{
    override fun eat() {
        println("${type}는 밥을 먹을 때 얌얌얌 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("헤엄속도 : ${swimSpeed}노트")
    }

    override fun swimming() {
        println("${type}가 헤엄을 칠 때 슈웅 슈융 하면서 헤엄을 친다.")
    }

    override fun hunt() {
        println("${type}가 사냥을 하면 으아아아아아!!! 하면서 사냥을 한다.")
    }

}

// 말 클래스
class Horse(type: String, size: String, runSpeed: Int):Run2(type, size, runSpeed){
    override fun running() {
        println("${type}은 이히히히힝~ 하며 달립니다.")
    }

    override fun eat() {
        println("${type}은 밥을 먹을 때 당근 당근 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

}
// 기린 클래스
class Giraffe(type: String, size: String, runSpeed: Int):Run2(type, size, runSpeed),Hunt{
    override fun eat() {
        println("${type}은 밥을 먹을 때 풀풀풀 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

    override fun running() {
        println("${type}은 영차~ 영차~ 하며 달립니다.")
    }

    override fun hunt() {
        println("${type}은 가즈아~ 하고 사냥을 한다.")
    }

}
// 호랑이 클래스
class Tiger(type: String, size: String,var foodCount : Int):Run,Animal(type, size) {
    override fun eat() {
        println("${type}은 밥을 먹을 때 아작 아작 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("먹이량 : ${foodCount}마리")
    }

    override fun running() {
        println("${type}은 헥~ 헥~ 하며 달립니다.")
    }
}

// 사자 클래스
class Lion(type: String,size: String, var foodCount: Int):Hunt,Animal(type, size){
    override fun eat() {
        println("${type}은 밥을 먹을 때 꿀꺽 꿀꺽 하고 먹는다.")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("사이즈 : $size")
        println("먹이량 : ${foodCount}마리")
    }

    override fun hunt() {
        println("${type}은 암컷아 사냥해와라~ 하고 사냥을 한다.")
    }

}

// 수영하는 동물
open abstract class Marine(type: String, size: String,var swimSpeed : Int) :Animal(type,size){
    abstract fun swimming()
}
// 뛰는 동물
open abstract class Run2(type: String, size: String,var runSpeed : Int) :Animal(type,size),Run

interface Run{
    fun running()
}

// 사냥 동물
interface Hunt {
    fun hunt()
}

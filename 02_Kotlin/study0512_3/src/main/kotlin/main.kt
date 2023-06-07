import java.util.Scanner

fun main (){

    //CarFatory에 접근할 변수를 생성한다.
    val carFatoryMgr = CarFatory()

    // 사용자에게 입력받기 위해 스캐너를 생성한다.
    val scan = Scanner(System.`in`)

    // 사용자에게 입력받은 숫자를 저장할 변수를 생성한다.
    var inputNum = 0

    // 자동차를 생성한다.
    // createCar은
    // 0이 아니면 반복적으로 호출한다.
    do{
        println("생산할 자동차를 선택해주세요")
        println("1. 붕붕, 2. 승용차, 3. 버스, 4. 트럭, 0. 생산종료")

        // 사용자에게 생산할 자동차의 번호를 입력받는다.
        inputNum = scan.nextInt()
        // 입력 받은 번호가 0이 아니라면 createCar에 입력받은 타입번호를 전달한다.
        if(inputNum != 0){
            carFatoryMgr.createCar(inputNum)
        }
    }while(inputNum != 0)

    // 생산된 자동차들의 총 대수와 종류 별 대수를 출력한다.
    carFatoryMgr.printCreateCarCount()

    // 생산된 자동차들의 종류를 출력한다.
    carFatoryMgr.printCarInfo()

    // 생산된 자동차들의 평균 속도와 총 탑승인원수를 출력한다.
    carFatoryMgr.printAvgspeedSeat()

    //연료 종류별 자동차들의 대수를 출력한다.
    carFatoryMgr.fuelKindCount()

}

// 자동차를 생산하는 클래스
class CarFatory{
    // 생산된 자동차를 저장 할 배열
    val carList = ArrayList<Car>()

    // 생산할 자동차의 타입을 입력받는다.
    fun createCar(typeNumber : Int){
        when(typeNumber){
            // 입력받은 타입 넘버에 따라 리스트에 생산한 자동차를 넣어준다.
            1 -> carList.add(Car("붕붕",300,"꽃향기",1))
            2 -> carList.add(Car("승용차",200,"휘발유",4))
            3 -> carList.add(Car("버스",150,"가스",50))
            else -> carList.add(Car("트럭",100,"가스",3))
            // else에는 4를 입력받았을때만 접근한다.
        }
    }

    // 생산한 자동차의 대수를 종류별로 출력한다.
    fun printCreateCarCount(){
        //총 생산 자동차 수 : 000대
        println("총 생산 자동차 수 : ${carList.size}대")

        // 붕붕의 생산량을 저장할 변수
        var bung = 0
        // 승용차의 생산량을 저장할 변수
        var basic = 0
        // 버스의 생산량을 저장할 변수
        var bus = 0
        // 트럭의 생산량을 저장할 변수
        var truck = 0

        // 반복문을 진행하면서 자동차 종류별 변수 값 증가 시킨다.
        for (car in carList){
            when(car.kind){
                "붕붕" -> bung++
                "승용차" -> basic++
                "버스" -> bus++
                "트럭" -> truck++
            }
        }

        //        붕붕 : 00대
        //        승용차 : 00대
        //        버스 : 00대
        //        트럭 : 00대
        println("붕붕 : ${bung}대")
        println("승용차 : ${basic}대")
        println("버스 : ${bus}대")
        println("트럭 : ${truck}대")
        println()
    }


    // 생산한 자동차의 정보를 출력한다.
    fun printCarInfo(){
        // 생산한 자동차의 정보가 담긴 리스트를 반복하며 자동차의 정보를 출력한다.
        for(car in carList){
            car.printCar()
            println() // 구분 편하기 하려고 사용함.
        }
    }


    // 생산된 자동차들의 평균속도와 총 탑승인원수를 출력한다.
    fun printAvgspeedSeat(){
        // 스피드를 저장할 변수
        var speedTotal = 0
        // 타습인원수를 저장할 변수
        var seatCount = 0

        // 리스트에 저장된 시트 수와 스피드를 누적한다.
        for(car in carList){
            seatCount += car.seat
            speedTotal += car.maxSpeed
        }

        // 평균을 구한다.
        val speedAvg = speedTotal / carList.size
        val seatAvg = seatCount / carList.size

        println("생산된 자동차들의 평균 속도 : ${speedAvg}km/h")
        println("생산된 자동차들의 총 탑승인원수 : ${seatAvg}명")
        println()

    }

    // 연료 별 자동차의 수를 출력한다.
    fun fuelKindCount(){
        // 각 연료별 저장할 변수 선언
        var flower = 0
        var gasoline = 0
        var gas = 0
        // 반복문을 진행하면서 자동차의 연료에 따라 변수에 값을 누적한다.
        for(car in carList){
            when(car.fuel){
                "꽃향기" -> flower++
                "휘발유" -> gasoline++
                "가스" -> gas++
            }
        }

//        연료가 꽃향기인 자동차의 수 : 00대
//        연료가 휘발유인 자동차의 수 : 00대
//        연료가 가스인 자동차의 수 : 00대

        // 출력형식에 맞게 각 연료별 자동차의 수를 출력한다.
        println("연료가 꽃향기인 자동차의 수 : ${flower}대")
        println("연료가 휘발유인 자동차의 수 : ${gasoline}대")
        println("연료가 가스인 자동차의 수 : ${gas}대")
        println()



    }


}

// 자동차 클래스 생성

//종류 : 트럭
//최대속도 : 100km/h
//연료 : 가스
//탑승인원수 : 3명

class Car(val kind:String, val maxSpeed : Int, val fuel: String, val seat: Int){

    // 자동차 클래스의 멤버변수를 출력하는 메서드
    fun printCar(){
//        println("""종류 : $kind
//            | 최대속도 : ${maxSpeed}km/h
//            | 연료 : $fuel
//            | 탑승인원수 : ${seat}명
//        """.trimIndent())
        println("종류 : $kind")
        println("최대속도 : ${maxSpeed}km/h")
        println("연료 : $fuel")
        println("탑승인원수 : ${seat}명")

    }

}

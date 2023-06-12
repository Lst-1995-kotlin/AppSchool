package com.test.android_study_zoo

open abstract class Animal (val kind : String, val eat: String, val name: String){
    abstract fun returnInfo(): String
}

class Elephant(kind: String, eat: String, name: String, val noseLength: String) : Animal(kind, eat, name){
    override fun returnInfo() : String {
        return """동물종류 : $kind
            |먹이 : $eat
            |이름 : $name
            |코의 길이 : ${noseLength}m
        """.trimMargin()
    }
}

class Cat(kind: String, eat: String, name: String, val CatPunchSpeed: String) : Animal(kind, eat, name){
    override fun returnInfo() : String {
        return """동물종류 : $kind
            |먹이 : $eat
            |이름 : $name
            |냥펀치 속도 : ${CatPunchSpeed}km/h
        """.trimMargin()
    }
}

class Dog(kind: String, eat: String, name: String, val DogPerforCount: String) : Animal(kind, eat, name){
    override fun returnInfo() : String {
        return """동물종류 : $kind
            |먹이 : $eat
            |이름 : $name
            |개인기 개수 : ${DogPerforCount}개
        """.trimMargin()
    }
}
package com.test.android56_exhomework.data

enum class FragmentMainName(var str:String){
    FRAGMENT_MAIN("mainFragment"),
    FRAGMENT_INPUT("inputFragment"),
}

enum class FragmentInputName(var str:String){
    FRAGMENT_BASEBALL_ADD("baseballAddFragment"),
    FRAGMENT_SOCCER_ADD("soccerAddFragment"),
    FRAGMENT_SWIMMING_ADD("swimmingAddFragment")
}



open class Student(val name: String)

class BaseballStd(name: String, val batting: String, val homerun: Int, val hit: Int ):Student(name)

class SoccerStd(name: String, val goal: Int, val assist: Int):Student(name)

class SwimmingStd(name: String, val howSwim : String):Student(name)
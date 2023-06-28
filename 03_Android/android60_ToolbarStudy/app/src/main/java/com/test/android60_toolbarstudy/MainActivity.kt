package com.test.android60_toolbarstudy

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android60_toolbarstudy.databinding.ActivityMainBinding

data class Student(val name: String, val age: Int, val korean: Int)

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding
    val stdAllList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(FragmentName.FRAGMENT_MAIN, false, false)


    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:FragmentName, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment: Fragment? = null

        // 이름으로 분기한다.
        when(name){
            FragmentName.FRAGMENT_MAIN -> {
                // Fragment 객체를 생성한다.
                newFragment = MainFragment()
            }
            FragmentName.FRAGMENT_INPUT ->{
                newFragment = InputFragment()
            }

        }

        if(newFragment != null) {
            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.mainContainerView, newFragment)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name.str)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name:FragmentName){
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    enum class FragmentName(var str:String){
        FRAGMENT_MAIN("FragmentMain"),
        FRAGMENT_INPUT("FragmentInput")
    }

}
package com.test.android56_exhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android56_exhomework.addfragment.BaseBallAddFragment
import com.test.android56_exhomework.addfragment.SoccerAddFragment
import com.test.android56_exhomework.addfragment.SwimmingAddFragment
import com.test.android56_exhomework.data.BaseballStd
import com.test.android56_exhomework.data.FragmentInputName
import com.test.android56_exhomework.data.FragmentMainName
import com.test.android56_exhomework.data.SoccerStd
import com.test.android56_exhomework.data.Student
import com.test.android56_exhomework.data.SwimmingStd


class MainActivity : AppCompatActivity() {

    val stdList = mutableListOf<Student>()

    // 스피너 값 변경에 따라 리스트 값을 갱신한다.
    var selStdList = mutableListOf<Student>()

    var selStdNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stdList.add(SoccerStd("축구1",5,5))
        stdList.add(BaseballStd("야구1","0.386",10,10))
        stdList.add(SwimmingStd("수영1","평형"))


        stdList.forEach{
            if(it is SwimmingStd){
                Log.d("멋사", "${it.howSwim}")
            }
        }

        replaceFragment(FragmentMainName.FRAGMENT_MAIN, false, false)
        //replaceFragment2(FragmentInputName.FRAGMENT_BASEBALL_ADD, false, false)

    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: FragmentMainName, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment: Fragment? = null

        // 이름으로 분기한다.
        when(name){
            FragmentMainName.FRAGMENT_MAIN ->{
                newFragment = MainFragment()
            }
            FragmentMainName.FRAGMENT_INPUT ->{
                newFragment = InputFragment()
            }
        }

        if(newFragment != null) {
            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

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
    fun removeFragment(name:FragmentMainName){
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

// ---------------------------------------------------------------------------------------------------

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment2(name: FragmentInputName, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment: Fragment? = null

        // 이름으로 분기한다.
        when(name){
            FragmentInputName.FRAGMENT_BASEBALL_ADD -> {
                newFragment = BaseBallAddFragment()
            }
            FragmentInputName.FRAGMENT_SOCCER_ADD ->{
                newFragment = SoccerAddFragment()
            }
            FragmentInputName.FRAGMENT_SWIMMING_ADD ->{
                newFragment = SwimmingAddFragment()
            }
        }

        if(newFragment != null) {
            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.InputContainerView, newFragment)

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
    fun removeFragment2(name:FragmentInputName){
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}
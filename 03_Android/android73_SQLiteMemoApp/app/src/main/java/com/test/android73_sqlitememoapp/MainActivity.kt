package com.test.android73_sqlitememoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android73_sqlitememoapp.MainFragments.AddFragment
import com.test.android73_sqlitememoapp.MainFragments.MainFragment
import com.test.android73_sqlitememoapp.MainFragments.ResultFragment
import com.test.android73_sqlitememoapp.MainFragments.UpdateFragment
import com.test.android73_sqlitememoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    companion object{
        val FRAGMEMT_MAIN = "fragmentMain"
        val FRAGMENT_ADD = "fragmentAdd"
        val FRAGMENT_RESULT = "fragmentResult"
        val FRAGMENT_UPDATE = "fragmentUpdate"
    }

    var memoList = mutableListOf<Memo>()
    var selKey = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(FRAGMEMT_MAIN, false, false)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            FRAGMEMT_MAIN -> {
                MainFragment()
            }
            FRAGMENT_ADD -> {
                AddFragment()
            }
            FRAGMENT_RESULT ->{
                ResultFragment()
            }
            FRAGMENT_UPDATE ->{
                UpdateFragment()
            }
            else -> Fragment()
        }

        if(newFragment != null) {
            // Fragment를 교체한다.
            fragmentTransaction.replace(R.id.mainContainerView, newFragment)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}

data class Memo(val key : Long, val date : String, var title : String, var contents: String)
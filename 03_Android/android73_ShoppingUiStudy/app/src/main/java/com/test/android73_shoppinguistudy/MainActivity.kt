package com.test.android73_shoppinguistudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android73_shoppinguistudy.MainActivity.Companion.SPLASHFRAGMENT
import com.test.android73_shoppinguistudy.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var activityMain2Launcher: ActivityResultLauncher<Intent>

    companion object{
        val SPLASHFRAGMENT = "splashFragment"
        val JOINSELECTFRAGMENT = "joinSelectFragment"
        val LOGINFRAGMENT = "loginFragment"
        val SIGNUPFRAGMENT = "signupFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(SPLASHFRAGMENT, false, false)


    }


    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            SPLASHFRAGMENT -> SplashFragment()
            JOINSELECTFRAGMENT -> JoinSelectFragment()
            LOGINFRAGMENT -> LoginFragment()
            SIGNUPFRAGMENT -> SignupFragment()
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
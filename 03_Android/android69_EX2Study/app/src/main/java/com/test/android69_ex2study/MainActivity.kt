package com.test.android69_ex2study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android69_ex2study.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.lang.Exception

data class Student(val name: String, val age: Int, val height: Int, val weight: Int) : Serializable

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var selNum = 0
    val stdList = mutableListOf<Student>()

    companion object{
        val MAIN_FRAGMENT = "mainFragment"
        val ADD_FRAGMENT = "addFragment"
        val RESULT_FRAGMENT = "resultFragment"
        val FILE_NAME = "data.dat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            replaceFragment(MAIN_FRAGMENT, false, false)
        }
    }

    fun getData(){
        var fis : FileInputStream
        try {
            fis = openFileInput(FILE_NAME)
        } catch (e: Exception){
            return
        }
        val ois = ObjectInputStream(fis)
        stdList.clear()
        while(true){
            try {
                stdList.add(ois.readObject() as Student)
            } catch (e: Exception){
                break
            }
        }
        ois.close()
        fis.close()
    }

    fun setData(){
        val fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)
        for(std in stdList){
            oos.writeObject(std)
        }
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            MAIN_FRAGMENT -> {
                MainFragment()
            }
            ADD_FRAGMENT -> {
                AddFragment()
            }
            RESULT_FRAGMENT -> {
                ResultFragment()
            }
            else -> {
                Fragment()
            }
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
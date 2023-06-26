package com.test.android55_fragmentstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.test.android55_fragmentstudy.databinding.ActivityMainBinding
import com.test.android55_fragmentstudy.databinding.FragmentMainBinding
import com.test.android55_fragmentstudy.databinding.StdBinding
import org.w3c.dom.Text


data class Student(val name: String, val age:String, val korean: String)

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var stdList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(FragmentName.FRAGMENT_MAIN, false, false, null)

    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: FragmentName,addToBackStack :Boolean, animate: Boolean, bundle : Bundle?){
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
            FragmentName.FRAGMENT_INPUT -> {
                newFragment = InputStdFragment()
            }
            FragmentName.FRAGMENT_PRINT -> {
                newFragment = PrintStdInfoFragment()
                if(bundle != null){
                    newFragment.arguments = bundle
                }
            }
        }

        if(newFragment != null) {
            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.MainContainerView, newFragment)

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
        supportFragmentManager.popBackStack(name.str, 0)
    }


    // Fragment 들을 구분하기 위한 값
    enum class FragmentName(val str:String){
        FRAGMENT_MAIN("MainFragment"),
        FRAGMENT_INPUT("InputStdFragment"),
        FRAGMENT_PRINT("PrintStdInfoFragment")
    }

    inner class RecyclerViewClass : RecyclerView.Adapter<RecyclerViewClass.ViewHolderClass>() {
        inner class ViewHolderClass (stdBinding: StdBinding) : RecyclerView.ViewHolder(stdBinding.root){

            var name : TextView
            var age : TextView
            var korean : TextView
            init{
                name = stdBinding.textViewStdName
                age = stdBinding.textViewStdAge
                korean = stdBinding.textViewStdKorean

                stdBinding.root.setOnClickListener {

                    val bundle = Bundle()
                    bundle.putString("name",stdList[adapterPosition].name)
                    bundle.putString("age",stdList[adapterPosition].age)
                    bundle.putString("korean",stdList[adapterPosition].korean)

                    replaceFragment(FragmentName.FRAGMENT_PRINT,true, true, bundle)

                }

            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val stdBinding = StdBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(stdBinding)

            val params = RecyclerView.LayoutParams(
                // 가로길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                // 세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            params.topMargin = 10

            stdBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return stdList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.name.text = "이름 : ${stdList[position].name}"
            holder.age.text = "나이 : ${stdList[position].age}"
            holder.korean.text = "국어점수 : ${stdList[position].korean}"
        }
    }


}
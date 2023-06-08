package com.test.android14_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android14_ex03.databinding.ActivityMainBinding
import kotlin.concurrent.thread

data class Student(val name:String, val age: Int, val kor:Int, val eng:Int, val math:Int){
    fun printInfo(): String{
        return "이름 : $name \n나이 : $age\n국어점수 : $kor\n영어점수 : $eng\n수학점수 : $math\n "
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val stdList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // 최초 포커스
            thread {
                SystemClock.sleep(500)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)
            }

            stdName.run{
                requestFocus()
                // 엔터키를 누른 경우
                setOnEditorActionListener { v, actionId, event ->
                    false
                }
            }
            stdAge.run{
                // 엔터키를 누른 경우
                setOnEditorActionListener { v, actionId, event ->
                    false
                }
            }
            korSco.run{
                // 엔터키를 누른 경우
                setOnEditorActionListener { v, actionId, event ->
                    false
                }
            }
            engSco.run{
                // 엔터키를 누른 경우
                setOnEditorActionListener { v, actionId, event ->
                    false
                }
            }
            mathSco.run{
                // 엔터키를 누른 경우
                setOnEditorActionListener { v, actionId, event ->

                    // 입력받은 학생의 정보를 리스트에 저장
                    val name = stdName.text.toString()
                    stdName.text.clear()

                    val age = stdAge.text.toString().toInt()
                    stdAge.text.clear()

                    val kor = korSco.text.toString().toInt()
                    korSco.text.clear()

                    val eng = engSco.text.toString().toInt()
                    engSco.text.clear()

                    val math = mathSco.text.toString().toInt()
                    mathSco.text.clear()

                    stdList.add(Student(name, age, kor, eng, math))

                    // 저장된 학생 수 변경
                    stdCount.text = "현재 저장된 학생 정보 : ${stdList.size}"

                    // 포커스 정보 넘김
                    stdName.requestFocus()
                    false
                }

                logBtn.run{
                    setOnClickListener {
                        var korTotal = 0
                        var engTotal = 0
                        var mathTotal = 0
                        for(std in stdList){
                            Log.i("studentInfo",std.printInfo())
                            korTotal += std.kor
                            engTotal += std.eng
                            mathTotal += std.math
                        }
                        Log.i("studentInfo","국어 총점 : $korTotal")
                        Log.i("studentInfo","영어 총점 : $engTotal")
                        Log.i("studentInfo","수학 총점 : $mathTotal\n ")

                        Log.i("studentInfo","국어 평균 : ${korTotal/stdList.size}")
                        Log.i("studentInfo","영어 평균 : ${engTotal/stdList.size}")
                        Log.i("studentInfo","수학 평균 : ${mathTotal/stdList.size}")

                        // 포커스 정보 넘김
                        stdName.requestFocus()
                        // 키보드 올림
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(currentFocus,0)
                    }
                }

            }
        }
    }


}
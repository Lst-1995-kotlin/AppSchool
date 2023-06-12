package com.test.android18_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.method.TextKeyListener.clear
import android.view.inputmethod.InputMethodManager
import com.test.android18_study.databinding.ActivityMainBinding
import kotlin.concurrent.thread

data class Student(val name: String, val age: Int, val menWomen: String, val hobby: String, val korScore: Int)
var stdList = ArrayList<Student>()

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }

            nameText.run{
                // 포커스 가져오기
                requestFocus()
            }
            ageLayoutText.run{
                setOnEditorActionListener { v, actionId, event ->
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    if(currentFocus != null){
                        imm.hideSoftInputFromWindow(windowToken,0)
                        currentFocus!!.clearFocus()
                    }
                    false
                }
            }
            korScore.run{

                setOnEditorActionListener { v, actionId, event ->

                    val name = nameText.text.toString()
                    val age = ageLayoutText.text.toString().toInt()
                    val menWomen = when(selectMenWomen.checkedRadioButtonId){
                        R.id.men -> "남자"
                        R.id.women -> "여자"
                        else -> "오류"
                    }


                    var hobby = StringBuilder()
                    if(hobby1.isChecked) hobby.append("게임")
                    if(hobby2.isChecked) hobby.append("축구")
                    if(hobby3.isChecked) hobby.append("영상시청")
                    if(hobby.isEmpty()) hobby.append("없음")

                    val std = Student(name, age, menWomen, hobby.toString(), korScore.text.toString().toInt())
                    stdList.add(std)

                    nameText.setText("")
                    ageLayoutText.setText("")
                    selectMenWomen.clearCheck()
                    hobby1.isChecked = false
                    hobby2.isChecked = false
                    hobby3.isChecked = false
                    korScore.text.clear()

                    nameText.requestFocus()

                    false

                }

            }

            printBtn.run{
                setOnClickListener {
                    var korTotal = 0
                    stdPrintTextView.text = ""
                    for(std in stdList){
                        stdPrintTextView.append("\n")
                        stdPrintTextView.append("이름 : ${std.name}\n")
                        stdPrintTextView.append("나이 : ${std.age}\n")
                        stdPrintTextView.append("성별 : ${std.menWomen}\n")
                        stdPrintTextView.append("취미 : ${std.hobby}\n")
                        stdPrintTextView.append("국어점수 : ${std.korScore}\n")
                        korTotal += std.korScore
                    }
                    stdPrintTextView.append("\n")
                    stdPrintTextView.append("국어점수 총점 : ${korTotal}\n")
                    stdPrintTextView.append("국어점수 평균 : ${korTotal/stdList.size}\n")
                }
            }

        }

    }

}
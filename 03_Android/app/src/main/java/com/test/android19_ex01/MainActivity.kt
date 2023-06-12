package com.test.android19_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.test.android19_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var imm : InputMethodManager

    val studentList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        activityMainBinding.run{
            // 이름을 입력하는 editText에 포커스를 준다.
            editTextName.requestFocus()

            thread {
                SystemClock.sleep(500)
                imm.showSoftInput(currentFocus,0)
            }

            // 국어점수 입력
            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->
                    // 학생정보를 담을 객체를 생성한다.
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val temp1 = radioGroupGender.checkedRadioButtonId
                    var gender = "남자"
                    if(temp1 == R.id.radioButtonWoman){
                        gender = "여자"
                    }
                    val korean = editTextKorean.text.toString().toInt()

                    val studentInfo = StudentInfo(name,age,gender,korean)

                    if(checkBoxGame.isChecked){
                        studentInfo.addHobby("게임")
                    }
                    if(checkBoxSoccer.isChecked){
                        studentInfo.addHobby("축구")
                    }
                    if(checkBoxMovie.isChecked){
                        studentInfo.addHobby("영화감상")
                    }

                    // 학생 정보듣 담는다.
                    studentList.add(studentInfo)

                    // 입력내용을 초기화 한다.
                    editTextName.setText("")
                    editTextAge.setText("")
                    checkBoxGame.isChecked = false
                    checkBoxMovie.isChecked = false
                    checkBoxSoccer.isChecked = false
                    radioGroupGender.check(R.id.radioButtonMan)
                    editTextKorean.setText("")

                    editTextName.requestFocus()

                    false
                }
            }

            // 버튼
            buttonResult.run{
                setOnClickListener {
                    // 키보드를 내린 후 포커스를 해제한다.
                    // 순서가 반대가 될 경우 nullPointerException 이 발생하게 된다.
                    if(currentFocus != null){
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                        currentFocus!!.clearFocus()
                    }

                    textViewResult.text = ""

                    // 국어 점수를 누적할 변수
                    var koreanTotal = 0

                    // 학생의 수 만큼 반복한다.
                    for(studuntInfo in studentList){
                        textViewResult.append("이름 : ${studuntInfo.name}\n")
                        textViewResult.append("나이 : ${studuntInfo.age}\n")

                        textViewResult.append("취미\n")
                        for(hobby in studuntInfo.hobby){
                            textViewResult.append(" ${hobby}\n")
                        }

                        textViewResult.append("성별 : ${studuntInfo.gender}\n")
                        textViewResult.append("국어점수 : ${studuntInfo.korean}\n")
                        koreanTotal += studuntInfo.korean
                        textViewResult.append("\n")
                    }

                    textViewResult.append("국어점수 총점 : ${koreanTotal}점\n")
                    textViewResult.append("국어점수 평균 : ${koreanTotal / studentList.size}점\n")

                }
            }

        }

    }

    // 학생 정보를 담을 데이터 클래스
    data class StudentInfo(var name:String, var age: Int, var gender: String, var korean:Int){
        val hobby = mutableListOf<String>()

        fun addHobby(a1: String){
            hobby.add(a1)
        }
    }

}
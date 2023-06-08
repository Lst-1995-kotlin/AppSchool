package com.test.android14_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.test.android14_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

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

            editTextNumber.run{
                // 포커스를 가져온다.
                requestFocus()

                // 엔터키를 눌렀을 경우
                setOnEditorActionListener { v, actionId, event ->
                    val num = text.toString().toInt()
                    // 기존에 있는 텍스트를 정리한다.
                    textView.text = ""
                    for(i in 1 .. 9){
                        textView.append("$num X $i = ${num*i}\n")
                    }
                    focusUnlock()
                    false
                }
            }

            button.run{
                setOnClickListener {
                    val num = editTextNumber.text.toString().toInt()
                    // 기존에 있는 텍스트를 정리한다.
                    textView.text = ""
                    for(i in 1 .. 9){
                        textView.append("$num X $i = ${num*i}\n")
                    }
                    focusUnlock()
                }
            }

        }
    }

    // 포커스 해제 함수
    fun focusUnlock(){

        SystemClock.sleep(10)

        // 포커스를 해제한다.
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if(currentFocus != null){
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
            currentFocus!!.clearFocus()
        }
    }

}
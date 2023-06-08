package com.test.android14_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.test.android14_ex02.databinding.ActivityMainBinding
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
                imm.showSoftInput(currentFocus,0)
            }

            editTextNumber.run{
                requestFocus()

                setOnEditorActionListener { v, actionId, event ->
                    true
                }
            }

            editTextNumber2.run{
                setOnEditorActionListener { v, actionId, event ->

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    if(currentFocus != null){
                        imm.hideSoftInputFromWindow(windowToken,0)
                        currentFocus!!.clearFocus()
                    }
                    false
                }
            }

            button.run {
                setOnClickListener {
                    textViewUpdate("+")
                }
            }
            button2.run {
                setOnClickListener {
                    textViewUpdate("-")
                }
            }
            button3.run {
                setOnClickListener {
                    textViewUpdate("*")
                }
            }
            button4.run {
                setOnClickListener {
                    textViewUpdate("/")
                }
            }

        }
    }

    fun textViewUpdate(oper: String) {
        when(oper){
            "+" -> {
                activityMainBinding.textView.run {
                    text = ""
                    editableText
                }
            }


            else -> return
        }
    }

}
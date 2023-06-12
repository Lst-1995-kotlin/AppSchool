package com.test.android21_compountcomponent2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android21_compountcomponent2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    // Toggle Button 의 ON/OFF 상태를 가져온다.
                    if(toggleButton.isChecked){
                        textView.text = "ON 상태입니다."
                    } else {
                        textView.text = "OFF 상태입니다."
                    }
                    // Switch의 ON/OFF 상태를 가져온다.
                    if(switch1.isChecked){
                        textView.append("Switch : ON 상태입니다.\n")
                    } else {
                        textView.append("Switch : OFF 상태입니다.\n")
                    }
                }
            }

            button2.run {
                setOnClickListener {
                    // Toggle Button 을 ON 상태로 설정한다.
                    toggleButton.isChecked = true
                    // Switch를 ON 상태로 설정한다.
                    switch1.isChecked = true
                }
            }

            button3.run{
                setOnClickListener {
                    // Toggle Button을 OFF 상태로 설정한다.
                    toggleButton.isChecked = false
                    // Switch를 OFF 상태로 설정한다.
                    switch1.isChecked = false
                }
            }

            button4.run{
                setOnClickListener {
                    // Toggle Button을 반전 시킨다.
                    toggleButton.toggle()
                    // Switch를 반전 시킨다.
                    switch1.toggle()
                }
            }

            // Toggle button의 ON/OFF 상태가 변경될 때의 리스너
            toggleButton.run{
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if(isChecked){
                        textView2.text = "Toggle 버튼이 ON 상태입니다.\n"
                    } else {
                        textView2.text = "Toggle 버튼이 OFF 상태입니다.\n"
                    }
                }
            }

            // Switch ON/OFF 상태가 변경될 때의 리스너
            switch1.run{
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if(isChecked){
                        textView2.append("Switch 가 ON 상태입니다.\n")
                    } else {
                        textView2.append("Switch 가 OFF 상태입니다.\n")
                    }
                }
            }

        }

    }
}
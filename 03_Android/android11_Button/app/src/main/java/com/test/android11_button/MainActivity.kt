package com.test.android11_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android11_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activitymainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitymainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activitymainBinding.root)

        activitymainBinding.run {
            button2.run{
                // 버튼의 문자열을 변경한다.
                text = "버튼 입니다."
                // 버튼을 눌렀을 때 반응하는 리스너
                setOnClickListener {
                    activitymainBinding.textView.text = "버튼을 눌렀습니다."
                }
            }
            imageButton2.run{
                // 이미지 버튼의 이미지를 변경한다.
                setImageResource(R.drawable.imgflag8)
                // 이미지 버튼을 눌렀을 때 반응하는 리스너
                setOnClickListener{
                    activitymainBinding.textView.text = "이미지 버튼을 눌렀습니다."
                }
            }
        }

    }
}
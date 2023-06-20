package com.test.android43_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.test.android43_study.databinding.ActivityMain2Binding
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity() {

    lateinit var activityMain2Binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMain2Binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(activityMain2Binding.root)

        activityMain2Binding.run{

            val temp = intent.getStringExtra("text")

           textView6.text = "${temp} 전달 잘 받았음."

            button2.run{
                setOnClickListener {

                    val resultIntent = Intent()
                    resultIntent.putExtra("text","액티비티 2에서 보낸 값")

                    setResult(RESULT_OK, resultIntent)

                    finish()
                }
            }

        }

    }
}
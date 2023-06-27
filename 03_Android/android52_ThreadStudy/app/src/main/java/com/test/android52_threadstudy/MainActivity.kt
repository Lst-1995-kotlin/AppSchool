package com.test.android52_threadstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.test.android52_threadstudy.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                timeThread()
            }

        }
    }

    fun timeThread(){
        val now = System.currentTimeMillis()
        thread {
            while (true){
                SystemClock.sleep(500)
                activityMainBinding.textView.text ="경과시간 : ${(System.currentTimeMillis() - now)/1000.0}초"
            }
        }

    }

}
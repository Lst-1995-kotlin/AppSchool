package com.test.android03_viewbindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android03_viewbindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.button.setOnClickListener {
            var sum = 0
            for(i in 1 .. 100){
                sum += i
            }
            activityMainBinding.textView.text = "1 ~ 100까지의 총 합 : $sum"
        }

        activityMainBinding.button2.setOnClickListener {
            var sum = 0
            for(i in 101 .. 200){
                sum += i
            }
            activityMainBinding.textView.text = "101 ~ 200까지의 총 합 :$sum"
        }

    }
}
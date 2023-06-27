package com.test.android53_boradcastreceiverstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android53_boradcastreceiverstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                val brIntent = Intent("com.test.studybr")
                brIntent.putExtra("data1", 200)
                brIntent.putExtra("data2", "앱1")
                sendBroadcast(brIntent)
            }
        }
    }
}
package com.test.android42_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android42_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activitymainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitymainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activitymainBinding.root)

        activitymainBinding.run{
            buttonMain.run {
                setOnClickListener {
                    // 다른 액티비티를 실행한다.
                    // Intent 객체를 생성한다.
                    val secondIntent = Intent (this@MainActivity, SecondActivity::class.java)
                    startActivity(secondIntent)
                }
            }
        }
    }
}
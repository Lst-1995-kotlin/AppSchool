package com.test.android42_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android42_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitysecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitysecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitysecondBinding.root)

        activitysecondBinding.run{
            buttonSecond.run {
                setOnClickListener {
                    // 현재 Activity를 종료하고 BackStack에서 제거한다.
                    finish()
                }
            }
        }

    }
}
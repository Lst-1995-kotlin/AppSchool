package com.test.android43_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.test.android43_study.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val COMEBACK = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run{
            button.setOnClickListener {
                val intent = Intent(this@MainActivity,MainActivity2::class.java)

                intent.putExtra("text","액티비티 1에서 보낸값")

                startActivityForResult(intent,COMEBACK)
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            COMEBACK ->{
                activityMainBinding.run{
                    when(resultCode){
                        RESULT_OK ->{
                            if(data != null){
                                val result = data.getStringExtra("text")
                                textView.text = result
                            }
                        }
                    }

                }
            }
        }
    }

}
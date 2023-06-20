package com.test.android43_study2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_study2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var activityMain2Binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMain2Binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(activityMain2Binding.root)

        activityMain2Binding.run{
            textView2.run {
                var check = intent.getStringExtra("text")
                if(check != null){
                    text = check
                }
            }

            button2.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("text", "액티비티2에서 보낸 값")
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}
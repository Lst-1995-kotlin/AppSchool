package com.test.android_memohomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.android_memohomework.databinding.ActivityMemoAddBinding

class MemoAddActivity : AppCompatActivity() {

    lateinit var activityMemoAddbinding : ActivityMemoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMemoAddbinding = ActivityMemoAddBinding.inflate(layoutInflater)
        setContentView(activityMemoAddbinding.root)

        //Log.d("멋사","MemoAddActivity 도착")

        activityMemoAddbinding.run{

            buttonMemoSave.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("title",editTextMemoTitle.text.toString())
                    resultIntent.putExtra("contents",editTextMemoContents.text.toString())
                    setResult(RESULT_OK,resultIntent)
                    finish()
                }
            }

            buttonMemoCancle.run{
                setOnClickListener {
                    finish()
                }
            }

        }

    }
}
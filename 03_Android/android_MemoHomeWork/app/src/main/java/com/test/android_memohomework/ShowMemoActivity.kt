package com.test.android_memohomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memohomework.databinding.ActivityShowMemoBinding

class ShowMemoActivity : AppCompatActivity() {

    lateinit var showMemoBinding: ActivityShowMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showMemoBinding = ActivityShowMemoBinding.inflate(layoutInflater)
        setContentView(showMemoBinding.root)

        showMemoBinding.run{

            textViewMemoTitle.text = intent.getStringExtra("title")
            textViewMemoContents.text = intent.getStringExtra("contents")

        }

    }
}
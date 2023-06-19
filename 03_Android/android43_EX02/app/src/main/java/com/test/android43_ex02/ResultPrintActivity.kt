package com.test.android43_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_ex02.databinding.ActivityResultPrintBinding

class ResultPrintActivity : AppCompatActivity() {

    lateinit var activityResultPrintBinding: ActivityResultPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultPrintBinding = ActivityResultPrintBinding.inflate(layoutInflater)
        setContentView(activityResultPrintBinding.root)

        activityResultPrintBinding.run{

            textViewFruitName.text = intent.getStringExtra("kind")
            textViewFruitCount.text = intent.getStringExtra("count")
            textViewFruitFrom.text = intent.getStringExtra("from")

            buttonGoMain.setOnClickListener {
                finish()
            }

        }
    }
}
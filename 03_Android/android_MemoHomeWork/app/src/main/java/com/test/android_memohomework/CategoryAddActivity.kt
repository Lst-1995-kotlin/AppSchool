package com.test.android_memohomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memohomework.databinding.ActivityCategoryAddBinding

class CategoryAddActivity : AppCompatActivity() {

    lateinit var activityCategoryAddBinding: ActivityCategoryAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCategoryAddBinding = ActivityCategoryAddBinding.inflate(layoutInflater)
        setContentView(activityCategoryAddBinding.root)

        activityCategoryAddBinding.run{
            buttonCategoryAdd.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("addCategory",editTextAddCategoryName.text.toString())
                    setResult(RESULT_OK,resultIntent)
                    finish()
                }
            }

            buttonCategoryCancel.run {
                setOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }

        }
    }
}
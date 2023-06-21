package com.test.android_memohomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memohomework.databinding.ActivityCategoryEditBinding


class CategoryEditActivity : AppCompatActivity() {

    lateinit var categoryEditBinding: ActivityCategoryEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryEditBinding = ActivityCategoryEditBinding.inflate(layoutInflater)
        setContentView(categoryEditBinding.root)

        categoryEditBinding.run {

            textViewNowAct.text = "카테고리 수정 액티비티"
            val oriName = intent.getStringExtra("oriName")
            if(oriName != null){
                textViewCategoryName.text = "수정 카테고리명 : $oriName"
            }

            buttonSave.run{
                setOnClickListener {
                    // 변경할 이름 전달
                    val resultIntent = Intent()
                    resultIntent.putExtra("newCategory",editTextChangeCategoryName.text.toString())
                    resultIntent.putExtra("oriCategory",oriName)
                    setResult(RESULT_OK, resultIntent)

                    finish()
                }
            }

            buttonCencle.run{
                setOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }

        }
    }
}
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


        }
    }
}
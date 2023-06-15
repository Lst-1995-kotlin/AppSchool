package com.test.android37_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android37_ex01.databinding.ActivityMainBinding

lateinit var activityMainBinding: ActivityMainBinding

fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)
}
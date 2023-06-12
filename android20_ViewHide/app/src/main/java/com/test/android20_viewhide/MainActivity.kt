package com.test.android20_viewhide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.android20_viewhide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run{
            imageView2.visibility = View.INVISIBLE

            // VISIBLE : View 가 보이도록
            // INVISIBLE : View 가 보이지 않지만 자리는 차지함.
            // GONE : View 가 보이지 않고 자리를 차지하지 않게함.

            button.run {
                setOnClickListener {
                    imageView.visibility = View.VISIBLE
                    //imageView2.visibility = View.INVISIBLE
                    imageView2.visibility = View.GONE
                }
            }

            button2.run {
                setOnClickListener {
                    //imageView.visibility = View.INVISIBLE
                    imageView.visibility = View.GONE
                    imageView2.visibility = View.VISIBLE
                }
            }

        }


    }
}
package com.test.android02_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        val button1ClickListener = Button1ClickListener()
        button1.setOnClickListener(button1ClickListener)

        val button2ClickListener = Button2ClickListener()
        button2.setOnClickListener(button2ClickListener)

        val button3ClickListener = Button3ClickListener()
        button3.setOnClickListener(button3ClickListener)

        val button4ClickListener = Button4ClickListener()
        button4.setOnClickListener(button4ClickListener)

    }

    inner class Button1ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            textView.text = "10 + 10 = ${10 + 10}"
        }
    }

    inner class Button2ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            textView.text = "10 - 10 = ${10 - 10}"
        }

    }

    inner class Button3ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            textView.text = "10 * 10 = ${10 * 10}"
        }

    }

    inner class Button4ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            textView.text = "10 / 10 = ${10 / 10}"
        }

    }
}
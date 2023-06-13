package com.test.android32_homework0613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SimpleAdapter
import com.test.android32_homework0613.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val stdViewList = intArrayOf(R.id.textViewStdName, R.id.textViewStdAge, R.id.textViewStdKor)
    val mainViewList = arrayOf(R.id.editTextMainName.toString(),R.id.editTextMainAge.toString(),R.id.editTextMainKor.toString())
    val dataList = ArrayList<HashMap<String,Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            editTextMainKor.run{
                setOnEditorActionListener { v, actionId, event ->
                    val map = HashMap<String,Any>()

                    map[editTextMainName.id.toString()] = "이름 : ${editTextMainName.text}"
                    map[editTextMainAge.id.toString()] = "나이 : ${editTextMainAge.text}"
                    map[editTextMainKor.id.toString()] = "국어 점수 : ${editTextMainKor.text}"

                    dataList.add(map)

                    listViewMain.adapter = SimpleAdapter(
                        this@MainActivity, dataList, R.layout.std, mainViewList, stdViewList
                    )

                    editTextMainName.text.clear()
                    editTextMainAge.text.clear()
                    editTextMainKor.text.clear()

                    clearFocus()

                    false
                }
            }

        }

    }
}
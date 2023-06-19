package com.test.android43_ex02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.test.android43_ex02.databinding.ActivityAddFruitBinding

class AddFruitActivity : AppCompatActivity() {

    lateinit var activityAddFruitBinding : ActivityAddFruitBinding

    val fruitNameList = arrayOf(
        "수박","사과","귤"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddFruitBinding = ActivityAddFruitBinding.inflate(layoutInflater)
        setContentView(activityAddFruitBinding.root)

        activityAddFruitBinding.run{
            buttonSave.run{

                setOnClickListener {
                    val kind = fruitKind.selectedItemPosition
                    val count = editTextFruitCount.text.toString()
                    val from = editTextFruitFrom.text.toString()

                    val resultIntent = Intent()
                    resultIntent.putExtra("kind", kind)
                    resultIntent.putExtra("count", count)
                    resultIntent.putExtra("from", from)

                    setResult(RESULT_OK,resultIntent)

                    finish()

                }

            }

            fruitKind.run{
                val a1 = ArrayAdapter<String>(
                    this@AddFruitActivity,
                    android.R.layout.simple_list_item_1,
                    fruitNameList
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                setSelection(0)
                adapter = a1

            }
        }

    }
}
package com.test.android43_study2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android43_study2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            val a1 = ActivityResultContracts.StartActivityForResult()
            activityResultLauncher = registerForActivityResult(a1){
                if(it.resultCode == RESULT_OK){
                    val result = it.data?.getStringExtra("text")
                    activityMainBinding.textView.text = "$result"
                }
            }

            button.run{
                setOnClickListener {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("text","액티비티 1에서 보낸 값")
                    activityResultLauncher.launch(intent)
                }
            }
        }

    }
}
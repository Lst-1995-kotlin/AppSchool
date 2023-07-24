package com.test.material05_fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.material05_fab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            fab1.setOnClickListener{
                textView.text = "FAB1 버튼을 눌렀습니다."

                // fab2 가 보이는 상태라면
                if(fab2.isShown){
                    fab2.hide()
                } else {
                    // fab가 보이지 않는 상태라면
                    fab2.show()
                }

                // fab3 이 펼쳐져 있는 상태라면
                if(fab3.isExtended){
                    fab3.shrink()
                } else {
                    // 접혀있는 상태라면
                    fab3.extend()
                }
            }
        }
    }
}
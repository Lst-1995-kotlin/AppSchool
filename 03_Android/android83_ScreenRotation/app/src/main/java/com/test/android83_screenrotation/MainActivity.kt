package com.test.android83_screenrotation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android83_screenrotation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        activityMainBinding.run{

            // 복원
            if(savedInstanceState != null){
                val str1 = savedInstanceState.getString("str1")
                textViewResult.text = str1
            }

            buttonLoginSubmit.setOnClickListener {
                editTextUserId.setText("하하하하")
                editTextUserPw.setText("aaaaaa")
            }

            buttonJoin.setOnClickListener {
                textViewResult.text = "버튼을 눌렀습니다."
            }
        }

        setContentView(activityMainBinding.root)


    }

    // 화면 회전이 발생했을 때 호출되는 메서드(프래그먼트도 동일하다)
    // 매개변수로 들어오는 번들 객체는 화면이 다시 만들어지고 onCreate 메서드를 호출할 때
    // 매개변수로 전달된다.
    // 여기에는 화면 회전 후 값이 유지되지 않는 View들에 대한 복원 작업을 할 수 있도록
    // 데이터를 저장하는 역활을 한다.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // 새로운 화면에 복원해야할 뷰의 값을 저장한다.
        outState.putString("str1", activityMainBinding.textViewResult.text.toString())
    }
}
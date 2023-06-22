package com.test.android46_toast

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.test.android46_toast.databinding.ActivityMainBinding
import com.test.android46_toast.databinding.SnackbarBinding
import com.test.android46_toast.databinding.ToastBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var snackBar1 : Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // Toast 객체를 생성한다.
                    val t1 = Toast.makeText(this@MainActivity, "기본 Toast 입니다.", Toast.LENGTH_SHORT)

                    // 메시지가 사라질 때 동작할 코드가 있다면...
                    // Android 11 부터
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                        t1.addCallback(object : Toast.Callback(){

                            override fun onToastShown() {
                                super.onToastShown()
                                textView.text = "기본 Toast 메세지가 나타났습니다."
                            }

                            override fun onToastHidden() {
                                super.onToastHidden()
                                textView.text = "기본 Toast 메세지가 사라졌습니다."
                            }
                        })
                    }

                    t1.show()
                }
            }

            button2.run {
                setOnClickListener {
                    // ViewBinding 객체를 생성한다.
                    val toastBinding = ToastBinding.inflate(layoutInflater)
                    toastBinding.run{
                        imageViewToast.setImageResource(R.drawable.imgflag1)
                        textViewToast.text = "Coustom Toast 입니다."

                        val t2 = Toast(this@MainActivity)
                        //View를 설정한다.
                        t2.view = toastBinding.root

                        // 배경
                        toastBinding.root.setBackgroundResource(android.R.drawable.screen_background_dark)
                        // 텍스트 뷰의 글자 색상
                        textViewToast.setTextColor(Color.WHITE)

                        // 위치설정 (화면의 중간으로부터 아래로 300)
                        t2.setGravity(Gravity.CENTER, 0 , 300)

                        // 시간 설정
                        t2.duration = Toast.LENGTH_LONG

                        t2.show()

                    }
                }
            }

            button3.run{
                setOnClickListener {
                    // SnackBar 객체를 생성한다.
                    // snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_LONG)
                    // snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_SHORT)
                    snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_INDEFINITE)

                    // SnackBar의 Callback
                    snackBar1.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
                        // SnackBar 가 나타날때
                        override fun onShown(transientBottomBar: Snackbar?) {
                            super.onShown(transientBottomBar)
                            textView.text = "SnackBar가 나타났습니다."
                        }

                        // SnackBar가 사라질 때
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)
                            textView.text = "SnackBar가 사라졌습니다."
                        }
                    })

                    // 메시지 색상
                    snackBar1.setTextColor(Color.RED)
                    // 배경색
                    snackBar1.setBackgroundTint(Color.BLUE)
                    // 애니메이션
                    // snackBar1.setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                    snackBar1.animationMode = Snackbar.ANIMATION_MODE_SLIDE

                    // Action 설정
                    snackBar1.setAction("Action1"){
                        activityMainBinding.textView2.text = "Action1을 눌렀습니다."
                    }

                    snackBar1.show()
                }
            }

            button4.run{
                setOnClickListener {
                    // snackBar1 변수가 초기화 되어 있다면..
                    if(::snackBar1.isInitialized){
                        // 현재 스낵바가 보여지고 있는 상태라면.
                        if(snackBar1.isShown == true){
                            // 스낵바를 사라지게 한다.
                            snackBar1.dismiss()
                        }
                    }
                }
            }

            button5.setOnClickListener {
                // 스낵바를 생성한다,.
                val snackbar2 = Snackbar.make(it, "Custom SnackBar", Snackbar.LENGTH_SHORT)
                // ViewBinding
                val snackbarBinding = SnackbarBinding.inflate(layoutInflater)

                //View 설정
                snackbarBinding.run{
                    imageViewSnackBar.setImageResource(R.drawable.imgflag1)
                    textViewSnackBar.text = "새로 추가된 View"
                    textViewSnackBar.setTextColor(Color.WHITE)
                }

                // SnackBar의 Layout을 추출하여 새로운 뷰를 추가한다.
                val snackbarLayout = snackbar2.view as Snackbar.SnackbarLayout
                snackbarLayout.addView(snackbarBinding.root)

                //SnackBar가 가지고 있는 TextView를 보이지 않게 한다.
                val t1 = snackbarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                t1.visibility = View.INVISIBLE

                snackbar2.show()
            }

        }
    }


}
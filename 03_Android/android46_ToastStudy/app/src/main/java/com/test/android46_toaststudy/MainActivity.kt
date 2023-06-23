package com.test.android46_toaststudy

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.test.android46_toaststudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button2.run{
                setOnClickListener {
                    val toast = Toast.makeText(this@MainActivity,"Toast메세지 두둥 등장",Toast.LENGTH_SHORT)

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

                        toast.addCallback(object : Toast.Callback(){
                            override fun onToastShown() {
                                super.onToastShown()
                                textView.text = "toast 메세지가 나타났다~!"
                            }

                            override fun onToastHidden() {
                                super.onToastHidden()
                                textView.text = "toast 메세지가 사라졌다~!"
                            }
                        })

                    }

                    toast.show()

                }
            }

            button3.run{
                setOnClickListener {
                    val snackbar = Snackbar.make(it,"Snackbar 두둥 등장~!",Snackbar.LENGTH_LONG)

                    snackbar.setTextColor(Color.WHITE)
                    snackbar.setBackgroundTint(Color.BLACK)
                    snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE

                    snackbar.setAction("눌러보기"){
                        activityMainBinding.textView2.text = "Snackbar의 액션을 눌렀어요"
                    }

                    snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
                        override fun onShown(transientBottomBar: Snackbar?) {
                            super.onShown(transientBottomBar)
                            activityMainBinding.textView2.text = "Snackbar가 나타났다"
                        }

                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)
                            activityMainBinding.textView2.text = "Snackbar가 사라졌다"
                        }
                    })

                    snackbar.show()
                }
            }

        }
    }
}
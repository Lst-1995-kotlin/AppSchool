package com.test.android73_miniproject3.Auth

import android.app.AlertDialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.FragmentAuthBinding
import kotlin.concurrent.thread

class AuthFragment : Fragment() {

    lateinit var fragmentAuthBinding: FragmentAuthBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAuthBinding = FragmentAuthBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentAuthBinding.run{

            editTextAuthPassword.requestFocus()
            thread {
                SystemClock.sleep(300)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextAuthPassword, 0)
            }

            toolbarAuth.run{
                title = "비밀번호 설정"
                setTitleTextColor(Color.WHITE)
            }

            buttonAuthPasswordSave.run{
                setOnClickListener {
                    if (editTextAuthPassword.text.toString() != editTextAuthPasswordCheck.text.toString()) {
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("입력오류")
                        builder.setMessage("입력 비밀번호와 체크 비밀번호가 \n 일치하지 않습니다.")
                        builder.setNegativeButton("확인", null)
                        builder.show()
                        return@setOnClickListener
                    }
                    if (editTextAuthPassword.text.length < 1 || editTextAuthPasswordCheck.text.length < 1) {
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("입력오류")
                        builder.setMessage("입력 비밀번호와 체크 비밀번호의 길이는\n 1글자 이상이여야 합니다.")
                        builder.setNegativeButton("확인", null)
                        builder.show()
                        return@setOnClickListener
                    }

                    val password = editTextAuthPasswordCheck.text.toString()
                    DAOAuth.insert(mainActivity, password)
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_CATEGORY_LIST, false, false)
                }
            }
        }

        return fragmentAuthBinding.root
    }

}
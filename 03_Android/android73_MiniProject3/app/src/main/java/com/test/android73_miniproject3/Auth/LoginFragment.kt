package com.test.android73_miniproject3.Auth

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.test.android73_miniproject3.DB.DAOCategory
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.databinding.FragmentLoginBinding
import kotlin.concurrent.thread


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity
    lateinit var imm : InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run {

            toolbarLogin.title = "로그인"
            toolbarLogin.setTitleTextColor(Color.WHITE)

            imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            fragmentLoginBinding.editTextNumberPassword.requestFocus()
            thread {
                SystemClock.sleep(600)
                imm.showSoftInput(fragmentLoginBinding.editTextNumberPassword, 0)
            }

            buttonLogin.setOnClickListener {
                if(DAOAuth.passWordCheck(mainActivity, editTextNumberPassword.text.toString())){
                    mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_CATEGORY_LIST,false, false)
                } else {
                    val builder = AlertDialog.Builder(mainActivity)
                    builder.setTitle("인증 오류")
                    builder.setMessage("알맞는 패스워드가 아닙니다.")
                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        fragmentLoginBinding.editTextNumberPassword.requestFocus()
                        thread {
                            SystemClock.sleep(100)
                            imm.showSoftInput(fragmentLoginBinding.editTextNumberPassword, 0)
                        }
                    }
                    builder.show()
                }
            }

            editTextNumberPassword.setOnEditorActionListener { v, actionId, event ->
                if(DAOAuth.passWordCheck(mainActivity, editTextNumberPassword.text.toString())){
                    mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_CATEGORY_LIST,false, false)
                } else {
                    val builder = AlertDialog.Builder(mainActivity)
                    builder.setTitle("인증 오류")
                    builder.setMessage("알맞는 패스워드가 아닙니다.")
                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        fragmentLoginBinding.editTextNumberPassword.requestFocus()
                        thread {
                            SystemClock.sleep(100)
                            imm.showSoftInput(fragmentLoginBinding.editTextNumberPassword, 0)
                        }
                    }
                    builder.show()
                }
                false
            }
        }
        return fragmentLoginBinding.root
    }


}
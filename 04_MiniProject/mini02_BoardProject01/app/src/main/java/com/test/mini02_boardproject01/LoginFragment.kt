package com.test.mini02_boardproject01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.mini02_boardproject01.DB.LoginDAO
import com.test.mini02_boardproject01.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run{
            toolbarLogin.run{
                title = "로그인"
            }
            buttonJoin.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, null)
            }
            buttonLogin.setOnClickListener {
//                if(textInputEditTextLoginUserId.text.isNullOrEmpty() || textInputEditTextLoginUserPw.text.isNullOrEmpty()) return@setOnClickListener
//                mainActivity.nowUserInfo = LoginDAO.LoginCheck(mainActivity, textInputEditTextLoginUserId.text.toString(), textInputEditTextLoginUserPw.text.toString())
//
//                if(mainActivity.nowUserInfo != null){
//                    mainActivity.removeFragment(MainActivity.LOGIN_FRAGMENT)
//                    mainActivity.replaceFragment(MainActivity.MAIN_BOARD_FRAGMENT, true, null)
//                } else {
//                    val builder = MaterialAlertDialogBuilder(mainActivity)
//                    builder.setTitle("로그인 오류")
//                    builder.setMessage("아이디 또는 패스워드를 확인하세요.")
//                    builder.setNegativeButton("확인",null)
//                    builder.show()
//                    return@setOnClickListener
//                }

                mainActivity.removeFragment(MainActivity.LOGIN_FRAGMENT)
                mainActivity.replaceFragment(MainActivity.MAIN_BOARD_FRAGMENT, true, null)
            }
        }

        return fragmentLoginBinding.root
    }


}
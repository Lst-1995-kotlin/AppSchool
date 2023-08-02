package com.test.mini02_boardproject01

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.mini02_boardproject01.databinding.FragmentJoinBinding
import kotlin.concurrent.thread


class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentJoinBinding.run{

            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            textInputEditTextJoinUserId.requestFocus()

            thread {
                SystemClock.sleep(200)
                imm.showSoftInput(textInputEditTextJoinUserId, 0)
            }

            // toolbar
            toolbarJoin.run{
                title = "회원가입"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }
            }

            textInputEditTextJoinUserPw2.run{
                setOnEditorActionListener { v, actionId, event ->
                    if(!check()) return@setOnEditorActionListener true
                    mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, null)
                    true
                }
            }

            // 다음 버튼
            buttonJoinNext.run{
                setOnClickListener {
                    if(!check()) return@setOnClickListener
                    mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, null)
                }
            }
        }

        return fragmentJoinBinding.root
    }

    fun check(): Boolean{
        if(!mainActivity.inputCheck(fragmentJoinBinding.textInputEditTextJoinUserId,"아이디")) return false
        if(!mainActivity.inputCheck(fragmentJoinBinding.textInputEditTextJoinUserPw, "패스워드")) return false
        if(!mainActivity.inputCheck(fragmentJoinBinding.textInputEditTextJoinUserPw2, "패스워드 확인란")) return false
        if(!mainActivity.equalCheck(fragmentJoinBinding.textInputEditTextJoinUserPw,
                fragmentJoinBinding.textInputEditTextJoinUserPw2)) return false

        mainActivity.joinUserId = fragmentJoinBinding.textInputEditTextJoinUserId.text.toString()
        mainActivity.joinUserPw = fragmentJoinBinding.textInputEditTextJoinUserPw2.text.toString()
        return true
    }

}









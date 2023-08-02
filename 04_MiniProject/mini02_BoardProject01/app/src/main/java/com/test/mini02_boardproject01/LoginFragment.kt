package com.test.mini02_boardproject01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.test.mini02_boardproject01.databinding.FragmentLoginBinding
import kotlin.concurrent.thread

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
            // toolbar
            toolbarLogin.run{
                title = "로그인"
            }

            // 회원가입 버튼
            buttonLoginJoin.run{
                setOnClickListener {
                    // JoinFragment를 보이게 한다.
                    mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, null)
                }
            }

            // 로그인 버튼
            buttonLoginSubmit.run{
                setOnClickListener {
                    if(!check()) return@setOnClickListener
                    loginCheck()
                }
            }
            textInputEditTextLoginUserPw.run{
                setOnEditorActionListener { v, actionId, event ->
                    if(!check()) return@setOnEditorActionListener true
                    loginCheck()
                    true
                }
            }
        }

        return fragmentLoginBinding.root
    }

    fun check():Boolean{
        if(!mainActivity.inputCheck(fragmentLoginBinding.textInputEditTextLoginUserId, "아이디")) return false
        if(!mainActivity.inputCheck(fragmentLoginBinding.textInputEditTextLoginUserPw, "패스워드")) return false
        return true
    }

    fun loginCheck(){
        val fireDataBase = FirebaseDatabase.getInstance()
        val fireUserRef = fireDataBase.getReference("UserInfo")
        val inputId = fragmentLoginBinding.textInputEditTextLoginUserId.text.toString()
        val inputPw = fragmentLoginBinding.textInputEditTextLoginUserPw.text.toString()

        var loginPossible = true

        fireUserRef.orderByChild("userId").equalTo(inputId).get().addOnCompleteListener {
            if(it.result.children.toList().isEmpty()){
                val bulider = MaterialAlertDialogBuilder(mainActivity)
                bulider.setTitle("아이디 오류")
                bulider.setMessage("존재하지 않는 아이디 입니다.")
                bulider.setNegativeButton("확인", null)
                bulider.show()
                loginPossible = false
            }

            for(user in it.result.children){
                val userId = user.child("userId").value.toString()
                val userPw = user.child("userPw").value.toString()
                if(inputPw != userPw){
                    val bulider = MaterialAlertDialogBuilder(mainActivity)
                    bulider.setTitle("패스워드 오류")
                    bulider.setMessage("올바르지 않은 패스워드 입니다.")
                    bulider.setNegativeButton("확인", null)
                    bulider.show()
                    loginPossible = false
                    break
                }
                val userNickname = user.child("userNickName").value.toString()
                val userAge = user.child("userAge").value.toString().toLong()
                val userHobby = mutableListOf<String>()
                val hobby = user.child("userHobby").value as List<*>
                hobby.forEach {
                    userHobby.add(it.toString())
                }

                mainActivity.nowLoginUser = UserInfo(userId, userPw, userNickname, userAge, userHobby)
            }
            if(loginPossible) mainActivity.replaceFragment(MainActivity.BOARD_MAIN_FRAGMENT, false, null)
        }
    }

}
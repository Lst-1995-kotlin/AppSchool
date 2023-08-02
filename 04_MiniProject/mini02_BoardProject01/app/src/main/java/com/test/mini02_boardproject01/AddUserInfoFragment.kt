package com.test.mini02_boardproject01

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.children
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.mini02_boardproject01.databinding.FragmentAddUserInfoBinding
import kotlin.concurrent.thread


class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddUserInfoBinding.run{

            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            textInputEditTextAddUserInfoNickName.requestFocus()

            thread {
                SystemClock.sleep(200)
                imm.showSoftInput(textInputEditTextAddUserInfoNickName, 0)
            }

            // toolbar
            toolbarAddUserInfo.run{
                title = "회원가입"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT)
                }
            }
            // 가입 완료 버튼
            buttonAddUserInfoSubmit.run{
                setOnClickListener {

                    if(!check())return@setOnClickListener

                    addUser()

                    mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }
            }

            // 취미 전체 체크박스
            materialCheckBoxAddUserInfoAll.run{
                setOnCheckedChangeListener { buttonView, isChecked ->
                    // 각 체크박스를 가지고 있는 레이아웃을 통해 그 안에 있는 View들의 체크 상태를 변경한다.
                    for(v1 in materialCheckBoxGroupUserInfo1.children){
                        // 형변환
                        v1 as MaterialCheckBox
                        if(isChecked){
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        } else {
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }

                    for(v1 in materialCheckBoxGroupUserInfo2.children){
                        // 형변환
                        v1 as MaterialCheckBox
                        if(isChecked){
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        } else {
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }
                }
            }

            // 다른 체크박스 들...
            // 반복문
            for(v1 in materialCheckBoxGroupUserInfo1.children){
                v1 as MaterialCheckBox
                v1.setOnCheckedChangeListener { buttonView, isChecked ->
                    setParentCheckBoxState()
                }
            }
            // 반복문
            for(v1 in materialCheckBoxGroupUserInfo2.children){
                v1 as MaterialCheckBox
                v1.setOnCheckedChangeListener { buttonView, isChecked ->
                    setParentCheckBoxState()
                }
            }

        }

        return fragmentAddUserInfoBinding.root
    }

    // 하위의 체크박스들의 상태를 보고 상위 체크 박스 상태를 셋팅한다.
    fun setParentCheckBoxState(){
        fragmentAddUserInfoBinding.run{

            // 체크박스의 개수를 구한다.
            val checkBoxCount = materialCheckBoxGroupUserInfo1.childCount + materialCheckBoxGroupUserInfo2.childCount
            // 체크되어 있는 체크박스의 개수
            var checkedCount = 0

            // 체크되어 있는 체크박스의 개수를 구한다.
            for(v1 in materialCheckBoxGroupUserInfo1.children){
                v1 as MaterialCheckBox
                if(v1.checkedState == MaterialCheckBox.STATE_CHECKED){
                    checkedCount++
                }
            }
            for(v1 in materialCheckBoxGroupUserInfo2.children){
                v1 as MaterialCheckBox
                if(v1.checkedState == MaterialCheckBox.STATE_CHECKED){
                    checkedCount++
                }
            }

            // 만약 체크되어 있는 것이 없다면
            if(checkedCount == 0){
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_UNCHECKED
            }
            // 모두 체크되어 있다면
            else if(checkedCount == checkBoxCount){
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_CHECKED
            }
            // 일부만 체크되어 있다면
            else {
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_INDETERMINATE
            }

        }
    }

    fun check():Boolean{

        if(!mainActivity.inputCheck(fragmentAddUserInfoBinding.textInputEditTextAddUserInfoNickName, "닉네임")) return false
        if(!mainActivity.inputCheck(fragmentAddUserInfoBinding.textInputEditTextAddUserInfoAge, "나이")) return false
        return true
    }

    fun addUser(){
        val fireDataBase = Firebase.database
        val userRef = fireDataBase.getReference("UserInfo")
        val newUserId = mainActivity.joinUserId!!
        val newUserPw = mainActivity.joinUserPw!!
        val newUserNickName = fragmentAddUserInfoBinding.textInputEditTextAddUserInfoNickName.text.toString()
        val newUserAge = fragmentAddUserInfoBinding.textInputEditTextAddUserInfoAge.text.toString().toLong()
        val newUserhobby = mutableListOf<String>()

        if(fragmentAddUserInfoBinding.materialCheckBoxAddUserInfoAll.checkedState != MaterialCheckBox.STATE_UNCHECKED){
            for(v1 in fragmentAddUserInfoBinding.materialCheckBoxGroupUserInfo1.children){
                // 형변환
                v1 as MaterialCheckBox
                if(v1.isChecked){
                    newUserhobby.add(v1.text.toString())
                }
            }
            for(v1 in fragmentAddUserInfoBinding.materialCheckBoxGroupUserInfo2.children){
                v1 as MaterialCheckBox
                if(v1.isChecked){
                    newUserhobby.add(v1.text.toString())
                }
            }
        }

        val newUserInfo = UserInfo(newUserId, newUserPw, newUserNickName, newUserAge, newUserhobby)

        userRef.push().setValue(newUserInfo)

    }

}

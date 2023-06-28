package com.test.android60_toolbarstudy

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri.Builder
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.text.isDigitsOnly
import com.test.android60_toolbarstudy.databinding.FragmentInputBinding
import kotlin.concurrent.thread


class InputFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentInputBinding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run{

            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextInputName,0)
            }

            toolbarInput.run{
                title = "입력 화면"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                // 백 버튼의 아이콘 색상을 변경한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                setNavigationOnClickListener {
                   mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_INPUT)
                }
            }

            editTextInputKor.run{
                setOnEditorActionListener { v, actionId, event ->

                    if(editTextInputName.length() == 0){
                        return@setOnEditorActionListener false
                    }
                    if(editTextInputAge.text.isNullOrEmpty()|| !editTextInputAge.text.isDigitsOnly() ||
                        editTextInputAge.text.toString().toInt() < 0){
                        return@setOnEditorActionListener false
                    }
                    if(editTextInputKor.text.isNullOrEmpty()|| !editTextInputKor.text.isDigitsOnly() ||
                        editTextInputKor.text.toString().toInt() < 0){
                        return@setOnEditorActionListener false
                    }

                    val name = editTextInputName.text.toString()
                    val age = editTextInputAge.text.toString().toInt()
                    val kor = editTextInputKor.text.toString().toInt()

                    val newStd = Student(name, age, kor)
                    mainActivity.stdAllList.add(newStd)
                    mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_INPUT)
                    false
                }
            }

        }

        return fragmentInputBinding.root
    }


}
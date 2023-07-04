package com.test.android69_ex2study

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.test.android69_ex2study.databinding.FragmentAddBinding
import kotlin.concurrent.thread


class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run{
            editTextAddName.requestFocus()
            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextAddName, 0)
            }

            editTextAddWeight.run{
                setOnEditorActionListener { v, actionId, event ->

                    if(editTextAddWeight.text.isNullOrEmpty() ||
                            editTextAddAge.text.isNullOrEmpty()||
                            editTextAddHeight.text.isNullOrEmpty()){
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("오류 메시지")
                        builder.setMessage("입력되지 않은 항목이 존재합니다.")
                        builder.setPositiveButton("확인", null)
                        builder.show()
                        return@setOnEditorActionListener false
                    }

                    val name = editTextAddName.text.toString()
                    val age = editTextAddAge.text.toString().toInt()
                    val weight = editTextAddWeight.text.toString().toInt()
                    val height = editTextAddHeight.text.toString().toInt()

                    val newStd = Student(name, age, height, weight)
                    mainActivity.stdList.add(newStd)
                    mainActivity.setData()

                    mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)

                    false
                }
            }

        }

        return fragmentAddBinding.root
    }

}
package com.test.android68_study

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android68_study.databinding.FragmentAddBinding
import kotlin.concurrent.thread


class AddFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAddBinding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity
        fragmentAddBinding = FragmentAddBinding.inflate(layoutInflater)

        fragmentAddBinding.run{
            editTextName.requestFocus()
            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextName,0)
            }

            buttonSave.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val kor = editTextKor.text.toString().toInt()

                val newInfo = Info(name, age, kor)
                infoList.add(newInfo)
                mainActivity.setInfoData()
                mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_ADD)
            }
        }

        return fragmentAddBinding.root
    }


}
package com.test.android66_codeviewstudy

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.test.android66_codeviewstudy.databinding.FragmentAddBinding
import java.lang.StringBuilder
import kotlin.concurrent.thread


class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    val hobbyList = mutableListOf<EditText>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddBinding = FragmentAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        fragmentAddBinding.run{

            editTextName.requestFocus()
            thread {
                SystemClock.sleep(100)
                imm.showSoftInput(editTextName, 0)
            }

            buttonHobbyAdd.setOnClickListener {

                if(hobbyList.isEmpty()){
                    hobbyList.add(editTextTextHobby)
                }

                val newEditText = EditText(mainActivity)
                newEditText.hint = "취미"

                hobbyList.add(newEditText)
                hobbyLayout.addView(newEditText)
                newEditText.requestFocus()
                thread {
                    SystemClock.sleep(100)
                    imm.showSoftInput(newEditText, 0)
                }

            }
            buttonSave.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextTextAge.text.toString().toInt()
                val hobby = StringBuilder()
                hobbyList.forEach {
                    hobby.appendLine(it.text.toString())
                    Log.d("멋사","${it.text}")
                }
                hobbyList.clear()
                val newHumen = Humen(name, age, hobby.toString())
                mainActivity.humenList.add(newHumen)
                mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_ADD)
            }
        }

        return fragmentAddBinding.root
    }


}
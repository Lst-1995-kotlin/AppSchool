package com.test.android72_sqlitestudy0705

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android72_sqlitestudy0705.databinding.FragmentAddBinding
import kotlin.concurrent.thread


class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddBinding = FragmentAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run{
            editTextAddName.requestFocus()
            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextAddName, 0)
            }

            buttonAddSave.run{
                setOnClickListener {
                    val name = editTextAddName.text.toString()
                    val age = editTextAddAge.text.toString().toInt()
                    val korean = editTextAddKorean.text.toString().toInt()
                    val newStd = Student(name, age, korean)
                    DAO.insertData(mainActivity, newStd)
                    mainActivity.removeFragment(MainActivity.FRAGMENT_ADD)
                }
            }
        }


        return fragmentAddBinding.root
    }


}
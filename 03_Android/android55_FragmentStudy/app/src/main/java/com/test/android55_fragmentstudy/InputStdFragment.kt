package com.test.android55_fragmentstudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android55_fragmentstudy.databinding.FragmentInputStdBinding
import com.test.android55_fragmentstudy.databinding.FragmentMainBinding


class InputStdFragment : Fragment() {

    lateinit var fragmentInputStdBinding: FragmentInputStdBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity

        fragmentInputStdBinding = FragmentInputStdBinding.inflate(layoutInflater)

        fragmentInputStdBinding.run {
            buttonInputSaveUserInfo.setOnClickListener {
                val name = editTextInputUserName.text.toString()
                val age = editTextInputUserAge.text.toString()
                val korean = editTextInputUserKorean.text.toString()
                val newStd = Student(name, age, korean)
                mainActivity.stdList.add(newStd)

                mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_INPUT)
                mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_MAIN, true, true, null)
            }
        }

        return fragmentInputStdBinding.root
    }



}
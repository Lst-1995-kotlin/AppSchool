package com.test.android55_fragmentstudy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.android55_fragmentstudy.databinding.FragmentPrintStdInfoBinding

class PrintStdInfoFragment : Fragment() {


    lateinit var fragmentPrintStdInfoBinding : FragmentPrintStdInfoBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPrintStdInfoBinding = FragmentPrintStdInfoBinding.inflate(layoutInflater)
        fragmentPrintStdInfoBinding.run{

            textViewPrintStdName.text = "이름 : ${arguments?.getString("name")}"
            textViewPrintStdAge.text = "나이 : ${arguments?.getString("age")}세"
            textViewPrintStdKorean.text = "국어점수 : ${arguments?.getString("korean")}점"

        }

        return fragmentPrintStdInfoBinding.root
    }

    override fun onPause() {
        super.onPause()

        mainActivity = activity as MainActivity
        mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_PRINT)

    }

}
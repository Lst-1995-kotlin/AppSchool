package com.test.android69_ex2study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android69_ex2study.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.run{
            textView.text = "이름 : ${mainActivity.stdList[mainActivity.selNum].name}"
            textView2.text = "나이 : ${mainActivity.stdList[mainActivity.selNum].age}살"
            textView3.text = "키 : ${mainActivity.stdList[mainActivity.selNum].height}cm"
            textView4.text = "몸무게 : ${mainActivity.stdList[mainActivity.selNum].weight}kg"
        }

        return fragmentResultBinding.root
    }


}
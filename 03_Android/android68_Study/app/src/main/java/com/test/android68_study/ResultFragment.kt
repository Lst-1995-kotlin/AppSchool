package com.test.android68_study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android68_study.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity


        fragmentResultBinding.run{
            textViewResultName.text = "이름 : ${infoList[mainActivity.selNum].name}"
            textViewResultAge.text = "나이 : ${infoList[mainActivity.selNum].age} 세"
            textViewResultKor.text = "국어점수 : ${infoList[mainActivity.selNum].kor}점"
        }

        return fragmentResultBinding.root
    }


}
package com.test.android72_sqlitestudy0705

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android72_sqlitestudy0705.databinding.FragmentResultBinding


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
            val student = DAO.selectData(mainActivity, mainActivity.selectKey)
            textViewResultName.text = "이름 : ${student.name}"
            textViewResultAge.text = "나이 : ${student.age}"
            textViewResultKorean.text = "국어점수 ${student.korean}"
        }


        return fragmentResultBinding.root
    }


}
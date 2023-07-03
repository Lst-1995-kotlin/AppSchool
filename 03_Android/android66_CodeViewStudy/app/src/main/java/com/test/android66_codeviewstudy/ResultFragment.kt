package com.test.android66_codeviewstudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android66_codeviewstudy.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.run{
            textViewResultName.text = "이름 : ${mainActivity.humenList[mainActivity.selectNum].name}"
            textViewResultAge.text = "나이 : ${mainActivity.humenList[mainActivity.selectNum].age} 살"
            textViewResultHobby.text ="취미 : ${mainActivity.humenList[mainActivity.selectNum].hobby} "

            buttonBack.setOnClickListener {
                mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_RESULT)
            }
        }

        return fragmentResultBinding.root
    }


}
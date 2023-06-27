package com.test.android56_zoostudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android56_zoostudy.databinding.FragmentResultBinding


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
            textViewName.text = "이름 :${mainActivity.animalList[mainActivity.selectNum].name}"
            textViewAge.text = "나이 :${mainActivity.animalList[mainActivity.selectNum].age}"
            textViewKind.text = "종류 :${mainActivity.animalList[mainActivity.selectNum].kind}"
            textViewWeight.text = "몸무게 :${mainActivity.animalList[mainActivity.selectNum].weight}"


            buttonBack.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
                mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN, true, true)
            }

            buttonRemove.setOnClickListener {
                mainActivity.animalList.removeAt(mainActivity.selectNum)
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
                mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN, true, true)
            }

        }


        return fragmentResultBinding.root
    }


}
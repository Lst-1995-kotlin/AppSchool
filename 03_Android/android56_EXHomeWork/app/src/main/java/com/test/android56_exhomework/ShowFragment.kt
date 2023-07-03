package com.test.android56_exhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.android56_exhomework.data.BaseballStd
import com.test.android56_exhomework.databinding.FragmentInputBinding
import com.test.android56_exhomework.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentShowBinding.run {
            val stdInfo = mainActivity.stdList[mainActivity.selStdNum]

            when(stdInfo){
                is BaseballStd ->{
                    textView.text = "이름 : ${stdInfo.name}\n"
                    textView.append("타율 : ${stdInfo.batting}\n")
                    textView.append("안타 : ${stdInfo.hit}\n")
                    textView.append("홈런 : ${stdInfo.homerun}\n")
                }
            }


        }

        return fragmentShowBinding.root
    }


}
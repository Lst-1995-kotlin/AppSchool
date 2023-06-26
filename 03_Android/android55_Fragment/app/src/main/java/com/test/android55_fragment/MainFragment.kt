package com.test.android55_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android55_fragment.databinding.ActivityMainBinding
import com.test.android55_fragment.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // Fragment가 보여줄 화면을 생성하는 메서드
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Fragment를 관리하는 Activity객체를 가져온다.
        mainActivity = activity as MainActivity
        // View Binding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        fragmentMainBinding.run {
            buttonMain1.setOnClickListener {
                // Fragment를 교체한다.
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }
        }

        return fragmentMainBinding.root
    }


}
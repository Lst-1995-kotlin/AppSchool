package com.test.android73_shoppinguistudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android73_shoppinguistudy.databinding.FragmentJoinSelectBinding


class JoinSelectFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentJoinSelectBinding: FragmentJoinSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentJoinSelectBinding = FragmentJoinSelectBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentJoinSelectBinding.run{
            buttonGoLogin.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.LOGINFRAGMENT, true, true)
            }
            buttonSingup.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.SIGNUPFRAGMENT, true, true)
            }
        }

        return fragmentJoinSelectBinding.root
    }

}
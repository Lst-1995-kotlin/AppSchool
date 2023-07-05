package com.test.android72_sqlitestudy0705

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android72_sqlitestudy0705.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)


        return fragmentResultBinding.root
    }


}
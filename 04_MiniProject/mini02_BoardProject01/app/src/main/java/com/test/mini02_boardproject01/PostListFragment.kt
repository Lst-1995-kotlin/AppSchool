package com.test.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mini02_boardproject01.databinding.FragmentMainBoardBinding
import com.test.mini02_boardproject01.databinding.FragmentPostListBinding


class PostListFragment : Fragment() {

    lateinit var fragmentPostListBinding: FragmentPostListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)

        val name = arguments?.getString("name")

        fragmentPostListBinding.run{

        }

        return fragmentPostListBinding.root
    }


}
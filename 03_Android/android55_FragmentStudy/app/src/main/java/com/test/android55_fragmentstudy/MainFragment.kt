package com.test.android55_fragmentstudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android55_fragmentstudy.databinding.FragmentMainBinding
import com.test.android55_fragmentstudy.databinding.StdBinding


class MainFragment : Fragment() {

    lateinit var mainActivity : MainActivity
    lateinit var mainFragmentMainBinding: FragmentMainBinding

    override fun onResume() {
        super.onResume()
        // 재시작 시 리사이클러 뷰 갱신한다.
        val adapter = mainFragmentMainBinding.MainFraRecyclerView.adapter as RecyclerView.Adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity

        mainFragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        mainFragmentMainBinding.run{

            buttonInputInfo.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_INPUT, true, true, null)
            }

            MainFraRecyclerView.run{
                adapter = mainActivity.RecyclerViewClass()

                layoutManager = LinearLayoutManager(context)

            }
        }


        return mainFragmentMainBinding.root
    }



}
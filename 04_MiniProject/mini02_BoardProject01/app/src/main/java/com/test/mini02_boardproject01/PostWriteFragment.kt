package com.test.mini02_boardproject01

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mini02_boardproject01.databinding.FragmentPostWriteBinding


class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainFragment: MainBoardFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            mainFragment = arguments?.getParcelable("mainFragment", MainBoardFragment::class.java)!!
        } else{
            mainFragment = arguments?.getParcelable("mainFragment")!!
        }

        fragmentPostWriteBinding.run{
            postWriteToolbar.run{
                setNavigationIcon(R.drawable.exit_to_app_24px)
                setNavigationOnClickListener {
                    mainFragment.removeFragment(MainBoardFragment.POST_WRITE_FRAGMENT)
                }
            }
        }

        return fragmentPostWriteBinding.root
    }


}
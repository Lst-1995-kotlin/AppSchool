package com.test.mini02_boardproject01

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mini02_boardproject01.databinding.FragmentPostReadBinding
import kotlinx.coroutines.Dispatchers.Main


class PostReadFragment : Fragment() {

    lateinit var mainFragment: MainBoardFragment
    lateinit var fragmentPostReadBinding: FragmentPostReadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            mainFragment = arguments?.getParcelable("mainFragment", MainBoardFragment::class.java)!!
        } else{
            mainFragment = arguments?.getParcelable("mainFragment")!!
        }

        fragmentPostReadBinding = FragmentPostReadBinding.inflate(layoutInflater)

        fragmentPostReadBinding.run{
            postReadTitleTextView.text = arguments?.getString("title")
            postReadContentsTextView.text = arguments?.getString("contents")

            postReadMaterialToolbar.run{
                setNavigationIcon(R.drawable.exit_to_app_24px)
                setNavigationOnClickListener {
                    mainFragment.removeFragment(MainBoardFragment.POST_READ_FRAGMENT)
                }
            }

        }

        return fragmentPostReadBinding.root
    }


}
package com.test.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mini02_boardproject01.databinding.FragmentAddUserInfoBinding


class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddUserInfoBinding.run{
            AddInfomaterialToolbar.run{
                title = "추가 정보 입력"
            }
            hobbyCheck.run{
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if(isChecked == true){
                        checkboxContainer.visibility = View.VISIBLE
                    } else {
                        checkboxContainer.visibility = View.GONE
                    }
                }
            }

            buttonJoinSucces.setOnClickListener {
                mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT)
                mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
            }
        }

        return fragmentAddUserInfoBinding.root
    }


}
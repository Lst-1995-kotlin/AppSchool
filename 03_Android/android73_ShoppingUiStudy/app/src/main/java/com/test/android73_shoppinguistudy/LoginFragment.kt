package com.test.android73_shoppinguistudy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android73_shoppinguistudy.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run{
            editTextLoginPassword.run{
                setOnEditorActionListener { v, actionId, event ->
                    mainActivity.removeFragment(MainActivity.LOGINFRAGMENT)
                    val intent = Intent(mainActivity, MainActivity2::class.java)
                    startActivity(intent)
                    false
                }
            }
        }

        return fragmentLoginBinding.root
    }

}
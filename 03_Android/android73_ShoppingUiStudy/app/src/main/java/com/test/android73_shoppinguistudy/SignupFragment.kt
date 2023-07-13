package com.test.android73_shoppinguistudy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.test.android73_shoppinguistudy.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSignupBinding.run{
            editTextCheckPassword.run{
                setOnEditorActionListener { v, actionId, event ->
                    mainActivity.removeFragment((MainActivity.SIGNUPFRAGMENT))
                    val intent = Intent(mainActivity, MainActivity2::class.java)
                    startActivity(intent)
                    ActivityCompat.finishAffinity(mainActivity)
                    false
                }
            }
        }

        return fragmentSignupBinding.root
    }





}
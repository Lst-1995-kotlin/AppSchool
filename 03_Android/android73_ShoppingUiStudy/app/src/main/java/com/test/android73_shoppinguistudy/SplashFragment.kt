package com.test.android73_shoppinguistudy

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.concurrent.thread

class SplashFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity

        thread {
            SystemClock.sleep(3000)
            mainActivity.replaceFragment(MainActivity.JOINSELECTFRAGMENT, true, true)
        }

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}
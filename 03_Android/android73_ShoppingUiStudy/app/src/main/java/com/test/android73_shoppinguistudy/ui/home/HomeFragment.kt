package com.test.android73_shoppinguistudy.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.android73_shoppinguistudy.MainActivity
import com.test.android73_shoppinguistudy.MainActivity2
import com.test.android73_shoppinguistudy.databinding.FragmentHomeBinding
import kotlin.concurrent.thread

class HomeFragment : Fragment() {


    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var mainActivity2: MainActivity2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        mainActivity2 = activity as MainActivity2

        var shoutDownCheck = false
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로가기 눌렀을 때 동작할 코드
                if(shoutDownCheck){
                    MainActivity().moveTaskToBack(true);
                    MainActivity().finishAndRemoveTask();
                    mainActivity2.moveTaskToBack(true);						// 태스크를 백그라운드로 이동
                    mainActivity2.finishAndRemoveTask();						// 액티비티 종료 + 태스크 리스트에서 지우기
                    android.os.Process.killProcess(android.os.Process.myPid());
                } else {
                    shoutDownCheck = true
                    Toast.makeText(requireActivity(),"종료하시려면 한번 더 눌러주세요", Toast.LENGTH_SHORT).show()
                    thread {
                        SystemClock.sleep(3000)
                        shoutDownCheck = false
                    }
                }
            }
        })

        return fragmentHomeBinding.root
    }



}
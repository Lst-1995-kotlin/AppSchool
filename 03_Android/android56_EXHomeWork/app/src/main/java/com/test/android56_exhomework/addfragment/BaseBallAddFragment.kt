package com.test.android56_exhomework.addfragment

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android56_exhomework.MainActivity
import com.test.android56_exhomework.R
import com.test.android56_exhomework.data.BaseballStd
import com.test.android56_exhomework.data.FragmentInputName
import com.test.android56_exhomework.data.FragmentMainName
import com.test.android56_exhomework.databinding.FragmentBaseBallAddBinding
import kotlin.concurrent.thread


class BaseBallAddFragment : Fragment() {

    lateinit var fragmentBaseBallAddBinding: FragmentBaseBallAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentBaseBallAddBinding = FragmentBaseBallAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentBaseBallAddBinding.run{
            editTextBaseballName.requestFocus()
            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextBaseballName, 0)
            }

            buttonBaseballStdAdd.setOnClickListener {
                val name = editTextBaseballName.text.toString()
                val batting = editTextBatting.text.toString().toDouble()/100.0
                val homerun = editTextHomerun.text.toString().toInt()
                val hit = editTextHit.text.toString().toInt()
                val newBaseballStd = BaseballStd(name, batting.toString(), homerun, hit)
                mainActivity.stdList.add(newBaseballStd)

                mainActivity.removeFragment2(FragmentInputName.FRAGMENT_BASEBALL_ADD)
                mainActivity.removeFragment(FragmentMainName.FRAGMENT_INPUT)
            }

        }

        return fragmentBaseBallAddBinding.root
    }


}
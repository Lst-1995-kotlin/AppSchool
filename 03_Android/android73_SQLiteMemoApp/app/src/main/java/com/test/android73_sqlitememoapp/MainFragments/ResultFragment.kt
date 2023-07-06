package com.test.android73_sqlitememoapp.MainFragments

import android.content.DialogInterface
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import com.test.android73_sqlitememoapp.DB.DAO
import com.test.android73_sqlitememoapp.MainActivity
import com.test.android73_sqlitememoapp.R
import com.test.android73_sqlitememoapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentSetting()
        fragmentEvent()

        return fragmentResultBinding.root
    }

    private fun fragmentEvent() {
        fragmentResultBinding.run {
            toolbarResult.run {
                inflateMenu(R.menu.result_menu)
                title = "메모 읽기"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_RESULT)
                }

                menu[0].setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_UPDATE, true, true)
                    false
                }
                menu[1].setOnMenuItemClickListener {
                    val builder = AlertDialog.Builder(mainActivity)
                    builder.setTitle("메모 삭제")
                    builder.setMessage("메모를 삭제 하겠습니까?")
                    builder.setNegativeButton("취소", null)
                    builder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                        DAO.deleteMemo(mainActivity, mainActivity.selKey)
                        mainActivity.removeFragment(MainActivity.FRAGMENT_RESULT)
                    }

                    builder.show()

                    false
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val selectMemo = DAO.select(mainActivity, mainActivity.selKey)
        fragmentResultBinding.textViewResult.text = "${selectMemo.title}\n"
        fragmentResultBinding.textViewResult.append("${selectMemo.date}\n")
        fragmentResultBinding.textViewResult.append("${selectMemo.contents}\n")

    }

    private fun fragmentSetting() {
        fragmentResultBinding.textViewResult.text = ""
    }


}


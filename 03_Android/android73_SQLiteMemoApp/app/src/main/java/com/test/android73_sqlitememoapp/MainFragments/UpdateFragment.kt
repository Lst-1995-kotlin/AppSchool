package com.test.android73_sqlitememoapp.MainFragments

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.test.android73_sqlitememoapp.DB.DAO
import com.test.android73_sqlitememoapp.MainActivity
import com.test.android73_sqlitememoapp.Memo
import com.test.android73_sqlitememoapp.R
import com.test.android73_sqlitememoapp.databinding.FragmentUpdateBinding
import kotlin.concurrent.thread


class UpdateFragment : Fragment() {

    lateinit var fragmentUpdateBinding: FragmentUpdateBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentUpdateBinding = FragmentUpdateBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val updateMemo = DAO.select(mainActivity, mainActivity.selKey)

        FragmentSetting(updateMemo)
        updatePaly(updateMemo)

        return fragmentUpdateBinding.root
    }

    private fun updatePaly(updateMemo: Memo) {
        fragmentUpdateBinding.run {

            toolbarEdit.run {
                inflateMenu(R.menu.update_menu)
                title = "메모 수정"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_UPDATE)
                }

                setOnMenuItemClickListener {

                    if(!editTextUpdateTitle.text.isNullOrEmpty()) updateMemo.title = editTextUpdateTitle.text.toString()
                    if(!editTextUpdateContents.text.isNullOrEmpty()) updateMemo.contents = editTextUpdateContents.text.toString()

                    DAO.update(mainActivity, mainActivity.selKey, updateMemo)

                    mainActivity.removeFragment(MainActivity.FRAGMENT_UPDATE)
                    false
                }
            }

        }
    }

    private fun FragmentSetting(updateMemo: Memo) {
        fragmentUpdateBinding.editTextUpdateTitle.hint = updateMemo.title
        fragmentUpdateBinding.editTextUpdateContents.hint = updateMemo.contents
        fragmentUpdateBinding.editTextUpdateTitle.requestFocus()
        thread {
            SystemClock.sleep(100)
            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(fragmentUpdateBinding.editTextUpdateTitle, 0)
        }

    }

}
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
import com.test.android73_sqlitememoapp.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.concurrent.thread


class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        addFragmentSetting()

        return fragmentAddBinding.root
    }

    private fun addFragmentSetting() {
        fragmentAddBinding.run {

            editTextTitle.requestFocus()
            thread {
                SystemClock.sleep(100)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextTitle, 0)
            }

            toolbarAdd.run {
                inflateMenu(R.menu.add_menu)
                title = "메모 추가"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_ADD)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setOnMenuItemClickListener {

                    if(!check(editTextTitle) || !check(editTextContents)) return@setOnMenuItemClickListener false

                    val key = System.currentTimeMillis()
                    val title = editTextTitle.text.toString()
                    val contents = editTextContents.text.toString()
                    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val now = date.format(Date())
                    val memo = Memo(key, now, title, contents)
                    DAO.insert(mainActivity, memo)

                    mainActivity.removeFragment(MainActivity.FRAGMENT_ADD)
                    false
                }
            }
        }
    }

    private fun check(editText: EditText) : Boolean{
        if(editText.text.isNullOrEmpty()){
            val kind = when(editText.id){
                fragmentAddBinding.editTextTitle.id -> "제목"
                fragmentAddBinding.editTextContents.id -> "내용"
                else -> ""
            }
            val builder = AlertDialog.Builder(mainActivity)
            builder.setTitle("입력 오류")
            builder.setMessage("${kind}에 한 글자 이상 입력해주세요.")
            builder.setNegativeButton("확인", null)
            builder.show()
            return false
        }
        return true
    }


}
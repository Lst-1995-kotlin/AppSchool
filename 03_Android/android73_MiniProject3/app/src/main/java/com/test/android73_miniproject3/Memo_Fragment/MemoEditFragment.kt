package com.test.android73_miniproject3.Memo_Fragment

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
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.test.android73_miniproject3.DATA.Memo
import com.test.android73_miniproject3.DB.DAOCategory
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.FragmentMemoEditBinding


class MemoEditFragment : Fragment() {

    lateinit var fragmentMemoEditBinding: FragmentMemoEditBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoEditBinding = FragmentMemoEditBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoEditBinding.run{

            editTextMemoEditTitle.hint = mainActivity.selectMemo.memoTitle
            editTextMemoEditContents.hint = mainActivity.selectMemo.memoContents

            toolbarMemoEdit.run{
                title = "메모 수정"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.memo_save_menu)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_EDIT)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setOnMenuItemClickListener {

                    if(!check(editTextMemoEditTitle) || !check(editTextMemoEditContents)) return@setOnMenuItemClickListener false

                    val oriMemo = mainActivity.selectMemo

                    val category = mainActivity.selectCategory
                    val title = editTextMemoEditTitle.text.toString()
                    val contents = editTextMemoEditContents.text.toString()
                    val updateTime = System.currentTimeMillis()
                    val editMemo = Memo(category, title, contents, updateTime)

                    DAOMemo.update(mainActivity, oriMemo, editMemo)
                    DAOCategory.updateTime(mainActivity, category, updateTime)
                    mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                    mainActivity.selectMemo = editMemo

                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_EDIT)

                    false
                }

            }

        }

        return fragmentMemoEditBinding.root
    }

    fun check(editText: EditText): Boolean{
        if(editText.text.isNullOrEmpty()){
            val builder = AlertDialog.Builder(mainActivity)
            builder.setTitle("입력오류")
            val id = when(editText.id){
                fragmentMemoEditBinding.editTextMemoEditTitle.id -> "제목"
                fragmentMemoEditBinding.editTextMemoEditContents.id -> "내용"
                else -> ""
            }
            builder.setMessage("${id}이 입력되지 않았습니다.")
            builder.setNegativeButton("확인", null)
            builder.show()
            return false
        }
        return true
    }

}
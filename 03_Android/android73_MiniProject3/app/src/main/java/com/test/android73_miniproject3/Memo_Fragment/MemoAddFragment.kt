package com.test.android73_miniproject3.Memo_Fragment

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
import com.test.android73_miniproject3.DATA.Memo
import com.test.android73_miniproject3.DB.DAOCategory
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.FragmentMemoAddBinding
import kotlin.concurrent.thread

class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding: FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoAddBinding.run{

            editTextMemoTitle.requestFocus()

            thread {
                SystemClock.sleep(200)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextMemoTitle, 0)
            }

            toolbarMemoAdd.run{
                inflateMenu(R.menu.memo_save_menu)
                title = "메모 추가"

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_ADD)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setOnMenuItemClickListener {

                    if(!check(editTextMemoTitle) ||
                        !check(editTextMemoContents)) return@setOnMenuItemClickListener false

                    val category = mainActivity.selectCategory
                    val title = editTextMemoTitle.text.toString()
                    val contents = editTextMemoContents.text.toString()
                    val updateTime = System.currentTimeMillis()

                    val newMemo = Memo(category, title, contents, updateTime)
                    DAOMemo.insert(mainActivity, newMemo)
                    DAOCategory.updateTime(mainActivity, category, updateTime)
                    mainActivity.memoList = DAOMemo.selectAll(mainActivity, category)
                    mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_ADD)

                    thread {
                        SystemClock.sleep(200)
                        if(mainActivity.currentFocus != null){
                            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(mainActivity.currentFocus?.windowToken, 0)
                        }
                    }

                    false
                }
            }
        }

        return fragmentMemoAddBinding.root
    }

    fun check(editText: EditText): Boolean{
        if(editText.text.isNullOrEmpty()){
            val builder = AlertDialog.Builder(mainActivity)
            builder.setTitle("입력오류")
            val id = when(editText.id){
                fragmentMemoAddBinding.editTextMemoTitle.id -> "제목"
                fragmentMemoAddBinding.editTextMemoContents.id -> "내용"
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
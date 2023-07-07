package com.test.android73_miniproject3.Memo_Fragment

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
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.FragmentMemoResultBinding
import java.text.SimpleDateFormat


class MemoResultFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentMemoResultBinding: FragmentMemoResultBinding

    override fun onResume() {
        super.onResume()
        fragmentMemoResultBinding.run {
            textViewResultTitle.text = mainActivity.selectMemo.memoTitle

            val dataFormat = SimpleDateFormat("yyyy-MM-dd")
            val updateTime = dataFormat.format(mainActivity.selectMemo.updateTime)
            textViewResultDate.text = updateTime
            textViewResultContents.text = mainActivity.selectMemo.memoContents
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoResultBinding = FragmentMemoResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoResultBinding.run{

            toolbarMemoResult.run{
                inflateMenu(R.menu.memo_edit_menu)
                title = "메모 보기"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_RESULT)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                menu[0].setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_MEMO_EDIT, true, true)
                    false
                }
                menu[1].setOnMenuItemClickListener {

                    val builder = AlertDialog.Builder(mainActivity)
                    builder.setTitle("삭제 메시지")
                    builder.setMessage("정말로 삭제하시겠습니까?")
                    builder.setPositiveButton("취소",null)
                    builder.setNegativeButton("삭제"){ dialogInterface: DialogInterface, i: Int ->
                        DAOMemo.delete(mainActivity, mainActivity.selectMemo)
                        mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_RESULT)
                    }
                    builder.show()
                    false
                }

            }

        }

        return fragmentMemoResultBinding.root
    }


}
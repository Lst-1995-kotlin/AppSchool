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
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android73_miniproject3.DATA.Memo
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.FragmentMemoListBinding
import com.test.android73_miniproject3.databinding.MemoRycRowBinding
import java.text.SimpleDateFormat


class MemoListFragment : Fragment() {

    lateinit var fragmentMemoListBinding: FragmentMemoListBinding
    lateinit var mainActivity: MainActivity

    override fun onResume() {
        super.onResume()
        mainActivity.memoList = DAOMemo.selectAll(mainActivity, mainActivity.selectCategory)
        fragmentMemoListBinding.memoRycView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoListBinding = FragmentMemoListBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoListBinding.run{
            toolbarMemoList.run{
                inflateMenu(R.menu.memo_add_menu)
                setTitleTextColor(Color.WHITE)
                title = "${mainActivity.selectCategory}카테고리 메모"

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.FRAGMENT_MEMO_LIST)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }


                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_MEMO_ADD, true, true)
                    false
                }

            }

            memoRycView.run{
                adapter = memoRecycAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }


        }


        return fragmentMemoListBinding.root
    }

    inner class memoRecycAdapter : RecyclerView.Adapter<memoRecycAdapter.MemoViewHolder>(){
        inner class MemoViewHolder(memoRycRowBinding: MemoRycRowBinding) : ViewHolder(memoRycRowBinding.root){
            var memoTitle : TextView
            var memoUpdateTiem : TextView
            init{
                memoTitle = memoRycRowBinding.textViewMemoTitile
                memoUpdateTiem = memoRycRowBinding.textViewMemoUpdateTime

                memoRycRowBinding.root.setOnClickListener {
                    mainActivity.selectMemo = mainActivity.memoList[adapterPosition]
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_MEMO_RESULT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
            val memoRycRowBinding = MemoRycRowBinding.inflate(layoutInflater)
            val memoViewHolder = MemoViewHolder(memoRycRowBinding)

            memoRycRowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return memoViewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.memoList.size
        }

        override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
            holder.memoTitle.text = mainActivity.memoList[position].memoTitle
            val dataFormat5 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val updateTime = dataFormat5.format(mainActivity.memoList[position].updateTime)
            holder.memoUpdateTiem.text = "갱신 시간 : $updateTime"
        }
    }

}
package com.test.android73_sqlitememoapp.MainFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android73_sqlitememoapp.DB.DAO
import com.test.android73_sqlitememoapp.MainActivity
import com.test.android73_sqlitememoapp.R
import com.test.android73_sqlitememoapp.databinding.FragmentMainBinding
import com.test.android73_sqlitememoapp.databinding.RowBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onResume() {
        super.onResume()
        mainActivity.memoList = DAO.selectAll(mainActivity)
        val adapter = fragmentMainBinding.mainRecycView.adapter as RecyclerView.Adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            toolbarMain.run{
                inflateMenu(R.menu.main_menu)

                title = "메모앱"
                setTitleTextColor(Color.WHITE)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_ADD, true, true)
                    false
                }
            }

            mainRecycView.run{
                adapter = MainRecycAdapte()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }

        }

        return fragmentMainBinding.root
    }

    inner class MainRecycAdapte : RecyclerView.Adapter<MainRecycAdapte.MainViewHolder>() {
        inner class MainViewHolder(rowBinding: RowBinding): ViewHolder(rowBinding.root){
            var textViewDate : TextView
            var textViewTitle : TextView
            init {
                textViewDate = rowBinding.textViewDate
                textViewTitle = rowBinding.textViewTitle

                rowBinding.root.setOnClickListener {
                    mainActivity.selKey = mainActivity.memoList[adapterPosition].key
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_RESULT, true, true)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

            val rowBinding = RowBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.memoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textViewDate.text = mainActivity.memoList[position].date
            holder.textViewTitle.text = mainActivity.memoList[position].title
        }
    }

}






















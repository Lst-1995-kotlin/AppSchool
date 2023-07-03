package com.test.android66_codeviewstudy

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
import com.test.android66_codeviewstudy.databinding.FragmentMainBinding
import com.test.android66_codeviewstudy.databinding.MainItemBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onResume() {
        super.onResume()
        val adapter = fragmentMainBinding.mainRecyclerView.adapter as RecyclerAdapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            toolbarMain.run{
                title = "메인메뉴"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_ADD, true, true)
                    false
                }
            }
            mainRecyclerView.run{
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))
            }
        }


        return fragmentMainBinding.root
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>(){
        inner class MainViewHolder(mainItemBinding: MainItemBinding) : RecyclerView.ViewHolder(mainItemBinding.root){
            var textView : TextView
            init{
                textView = mainItemBinding.textViewName
                // 클릭이벤트
                mainItemBinding.root.setOnClickListener {
                    mainActivity.selectNum = adapterPosition
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_RESULT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val mainItemBinding = MainItemBinding.inflate(layoutInflater)
            val viewHolder = MainViewHolder(mainItemBinding)

            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            mainItemBinding.root.layoutParams = params

            return viewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.humenList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textView.text = mainActivity.humenList[position].name
        }
    }

}















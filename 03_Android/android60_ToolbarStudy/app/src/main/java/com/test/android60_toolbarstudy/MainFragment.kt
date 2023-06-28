package com.test.android60_toolbarstudy

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
import com.test.android60_toolbarstudy.databinding.FragmentMainBinding
import com.test.android60_toolbarstudy.databinding.MainRycItemBinding

class MainFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            mainFraRycView.run{
                adapter = MainFraRecyclerAdapter()
                layoutManager = LinearLayoutManager(mainActivity)

                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))
            }
            toolbarMain.run {
                title = "메인메뉴"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_INPUT, true, true)
                    false
                }

            }

        }

        return fragmentMainBinding.root
    }

    inner class MainFraRecyclerAdapter : RecyclerView.Adapter<MainFraRecyclerAdapter.MainFraViewHolder>(){
        inner class MainFraViewHolder(mainRycItemBinding: MainRycItemBinding) : ViewHolder(mainRycItemBinding.root){
            var textViewName : TextView
            init{
                textViewName = mainRycItemBinding.textViewName
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFraViewHolder {
            val mainRycItemBinding = MainRycItemBinding.inflate(layoutInflater)
            val mainFraViewHolder = MainFraViewHolder(mainRycItemBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            mainRycItemBinding.root.layoutParams = params

            return mainFraViewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.stdAllList.size
        }

        override fun onBindViewHolder(holder: MainFraViewHolder, position: Int) {
            holder.textViewName.text = mainActivity.stdAllList[position].name
        }
    }


}
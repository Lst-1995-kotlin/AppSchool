package com.test.android68_study

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android68_study.databinding.FragmentMainBinding
import com.test.android68_study.databinding.MainItemBinding
import kotlin.concurrent.thread

class MainFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        mainActivity.getInfoData()

        fragmentMainBinding.run{
            toolbarMain.run{
                title = "메인 메뉴"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    // 클릭하면 입력화면으로 이동.
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_ADD, true, true)
                    false
                }
            }

            mainRecycView.run{
                adapter = RecycViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))
            }

        }


        return fragmentMainBinding.root
    }

    inner class RecycViewAdapter : RecyclerView.Adapter<RecycViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(mainItemBinding: MainItemBinding) : RecyclerView.ViewHolder(mainItemBinding.root){
            val name : TextView
            init{
                name = mainItemBinding.textViewName
                // 아이템 클릭 이벤트
                mainItemBinding.root.setOnClickListener {
                    mainActivity.selNum = adapterPosition
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_RESULT, true, true)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

            val mainItemBinding = MainItemBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(mainItemBinding)

            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )


            mainItemBinding.root.layoutParams = params


            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return infoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.name.text = "${infoList[position].name}의 정보"
        }
    }


}
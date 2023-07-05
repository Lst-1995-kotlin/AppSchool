package com.test.android72_sqlitestudy0705

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
import com.test.android72_sqlitestudy0705.databinding.FragmentMainBinding
import com.test.android72_sqlitestudy0705.databinding.RowBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding : FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onResume() {
        super.onResume()
        mainActivity.stdList = DAO.selectAllData(mainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            toolbarMain.run {
                inflateMenu(R.menu.main_menu)
                title = "메인 메뉴"
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_ADD, true, true)
                    false
                }
            }

            mainRecycView.run{
                adapter = MainRecycAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }

        }

        return fragmentMainBinding.root
    }


    inner class MainRecycAdapter : RecyclerView.Adapter<MainRecycAdapter.MainViewHolder>() {
        inner class MainViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root){
            var textViewName : TextView
            init{
                textViewName = rowBinding.textViewName

                rowBinding.root.setOnClickListener {
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
            return mainActivity.stdList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textViewName.text = mainActivity.stdList[position].name
        }
    }

}
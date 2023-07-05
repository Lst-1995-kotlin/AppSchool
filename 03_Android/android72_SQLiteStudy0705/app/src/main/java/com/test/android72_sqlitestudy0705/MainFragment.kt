package com.test.android72_sqlitestudy0705

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
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
                    mainActivity.selectKey = mainActivity.stdList[adapterPosition].timeKey
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_RESULT, true, true)
                }

                // 항목 하나의 View에 컨텍스트 메뉴 생성 이벤트를 붙혀준다.
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    mainActivity.menuInflater.inflate(R.menu.row_menu, menu)
                    menu[0].setOnMenuItemClickListener {

                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("삭제 메시지")
                        builder.setMessage("정말 삭제하시겠습니까?")
                        builder.setNegativeButton("취소",null)
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            mainActivity.selectKey = mainActivity.stdList[adapterPosition].timeKey
                            DAO.removeData(mainActivity, mainActivity.selectKey)
                            mainActivity.stdList = DAO.selectAllData(mainActivity)
                            fragmentMainBinding.mainRecycView.adapter?.notifyDataSetChanged()
                        }
                        builder.show()


                        false
                    }

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
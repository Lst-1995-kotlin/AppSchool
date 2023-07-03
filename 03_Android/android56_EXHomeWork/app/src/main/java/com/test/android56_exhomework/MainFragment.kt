package com.test.android56_exhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android56_exhomework.data.BaseballStd
import com.test.android56_exhomework.data.FragmentInputName
import com.test.android56_exhomework.data.FragmentMainName
import com.test.android56_exhomework.data.SoccerStd
import com.test.android56_exhomework.data.Student
import com.test.android56_exhomework.data.SwimmingStd
import com.test.android56_exhomework.databinding.FragmentMainBinding
import com.test.android56_exhomework.databinding.MainfrastdrowBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    val stdClubList = arrayOf(
        "전체 보기","야구부", "축구부", "수영부"
    )

    override fun onResume() {
        super.onResume()
        val rvAdapter = fragmentMainBinding.recyclerMain.adapter as RecyclerView.Adapter
        rvAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        mainActivity.selStdList = mainActivity.stdList

        fragmentMainBinding.run{

            spinnerMain.run{
                val a1 = ArrayAdapter<String>(
                    mainActivity,android.R.layout.simple_list_item_1,stdClubList
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1

                setSelection(0)

                 onItemSelectedListener = object :OnItemSelectedListener {
                     override fun onItemSelected(
                         parent: AdapterView<*>?,
                         view: View?,
                         position: Int,
                         id: Long
                     ) {
                         when(selectedItemPosition) {
                             0 -> {
                                 mainActivity.selStdList = mainActivity.stdList
                                 mainActivity.selStdNum = 0
                             }

                             1 -> {
                                 val baseballStdList = mutableListOf<Student>()
                                 for (std in mainActivity.stdList) {
                                     if (std is BaseballStd) {
                                         baseballStdList.add(std)
                                     }
                                 }
                                 mainActivity.selStdList = baseballStdList
                                 mainActivity.selStdNum = 1
                             }

                             2 -> {
                                 val soccerStdList = mutableListOf<Student>()
                                 for (std in mainActivity.stdList) {
                                     if (std is SoccerStd) {
                                         soccerStdList.add(std)
                                     }
                                 }
                                 mainActivity.selStdList = soccerStdList
                                 mainActivity.selStdNum = 2
                             }

                             3 -> {
                                 val swimmingStdList = mutableListOf<Student>()
                                 for (std in mainActivity.stdList) {
                                     if (std is SwimmingStd) {
                                         swimmingStdList.add(std)
                                     }
                                 }
                                 mainActivity.selStdList = swimmingStdList
                                 mainActivity.selStdNum = 3
                             }
                         }
                         val rvAdapter = recyclerMain.adapter as RecyclerView.Adapter
                         rvAdapter.notifyDataSetChanged()
                     }

                     override fun onNothingSelected(parent: AdapterView<*>?) {
                         //TODO("Not yet implemented")
                     }

                 }

            }

            recyclerMain.run{
                adapter = MainRecyclerAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))
            }

            buttonToInput.setOnClickListener {
                mainActivity.replaceFragment(FragmentMainName.FRAGMENT_INPUT, true, true)
            }

        }


        return fragmentMainBinding.root
    }

    inner class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>(){
        inner class MainViewHolder(mainfrastdrowBinding: MainfrastdrowBinding) : ViewHolder(mainfrastdrowBinding.root){
            var textViewStdName : TextView
            init{
                textViewStdName = mainfrastdrowBinding.textViewStdName

                mainfrastdrowBinding.root.setOnClickListener {
                    mainActivity.selStdNum = mainActivity.stdList.indexOfFirst {
                        it.name == mainActivity.selStdList[adapterPosition].name
                    }
                    mainActivity.replaceFragment(FragmentMainName.FRAGMENT_SHOW, true, true)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val mainfrastdrowBinding = MainfrastdrowBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(mainfrastdrowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            mainfrastdrowBinding.root.layoutParams = params

            return mainViewHolder

        }

        override fun getItemCount(): Int {
            return mainActivity.selStdList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textViewStdName.text = mainActivity.selStdList[position].name
        }
    }

}
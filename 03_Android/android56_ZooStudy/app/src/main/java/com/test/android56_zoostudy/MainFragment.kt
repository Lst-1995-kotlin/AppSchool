package com.test.android56_zoostudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android56_zoostudy.databinding.FragmentMainBinding
import com.test.android56_zoostudy.databinding.MainrecycrowBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding : FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onStart() {
        super.onStart()

        val adapter = fragmentMainBinding.recycMain.adapter as MainRecycAdapter
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
            recycMain.run {
                adapter = MainRecycAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
            buttonMainAddAnimal.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }
        }

        return fragmentMainBinding.root
    }

    inner class MainRecycAdapter : RecyclerView.Adapter<MainRecycAdapter.MainViewHolderClass>() {
        inner class MainViewHolderClass(mainrecycrowBinding: MainrecycrowBinding) : ViewHolder(mainrecycrowBinding.root){
            var name : TextView
            var kind : TextView
            init{
                name = mainrecycrowBinding.textViewAnimalName
                kind = mainrecycrowBinding.textViewAnimalKind

                mainrecycrowBinding.root.setOnClickListener {
                    mainActivity.selectNum = adapterPosition
                    mainActivity.removeFragment(FragmentName.FRAGMENT_MAIN)
                    mainActivity.replaceFragment(FragmentName.FRAGMENT_RESULT, true, true)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val mainrecycrowBinding = MainrecycrowBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolderClass(mainrecycrowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            mainrecycrowBinding.root.layoutParams = params

            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.animalList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.kind.text = mainActivity.animalList[position].name
            holder.name.text = mainActivity.animalList[position].kind
        }
    }

}
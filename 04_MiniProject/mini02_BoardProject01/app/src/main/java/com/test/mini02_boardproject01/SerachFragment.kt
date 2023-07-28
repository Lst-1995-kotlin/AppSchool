package com.test.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mini02_boardproject01.databinding.FragmentSerachBinding
import com.test.mini02_boardproject01.databinding.RowBinding


class SerachFragment : Fragment() {

    lateinit var fragmentSerachBinding: FragmentSerachBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSerachBinding = FragmentSerachBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSerachBinding.run{
            searchfragmentRyc.run{
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentSerachBinding.root
    }
    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding: RowBinding): RecyclerView.ViewHolder(rowBinding.root){
            var textView: TextView
            init{
                textView = rowBinding.textView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return mainActivity.tempList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textView.text = mainActivity.tempList[position]
        }
    }

}
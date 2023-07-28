package com.test.mini02_boardproject01

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mini02_boardproject01.databinding.FragmentMainBoardBinding
import com.test.mini02_boardproject01.databinding.FragmentPostListBinding
import com.test.mini02_boardproject01.databinding.RowBinding
import com.test.mini02_boardproject01.databinding.RowPostListBinding


class PostListFragment : Fragment() {

    lateinit var fragmentPostListBinding: FragmentPostListBinding
    lateinit var mainFragment: MainBoardFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            mainFragment = arguments?.getParcelable("mainFragment", MainBoardFragment::class.java)!!
        } else{
            mainFragment = arguments?.getParcelable("mainFragment")!!
        }


        fragmentPostListBinding.run{

            searchBarPostList.run{
                inflateMenu(R.menu.post_list_add_menu)

                menu[0].setOnMenuItemClickListener {
                    val newBundle = Bundle()
                    newBundle.putParcelable("mainFragment", mainFragment)
                    mainFragment.replaceFragment(MainBoardFragment.POST_WRITE_FRAGMENT, true, false, newBundle)
                    true
                }

            }

            recyclerViewPostListAll.run{
                adapter = AllRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(context)
            }

            recyclerViewPostListResult.run{
                adapter = ResultRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(context)
            }

        }

        return fragmentPostListBinding.root
    }

    // 모든 게시글 목로그을 보여주는 리사이클러 뷰의 어댑터
    inner class AllRecyclerViewAdapter: RecyclerView.Adapter<AllRecyclerViewAdapter.AllViewHolder>(){
        inner class AllViewHolder(rowPostListBinding: RowPostListBinding): RecyclerView.ViewHolder(rowPostListBinding.root){
            val rowPostListSubject: TextView
            val rowPostListNickName: TextView
            init{
                rowPostListSubject = rowPostListBinding.rowPostListSubject
                rowPostListNickName = rowPostListBinding.rowPostListNickName

                rowPostListBinding.root.setOnClickListener {
                    val newBundle = Bundle()
                    newBundle.putString("title", "제목입니다 : $adapterPosition")
                    newBundle.putString("contents", "작성자 : $adapterPosition")
                    newBundle.putParcelable("mainFragment", mainFragment)
                    mainFragment.replaceFragment(MainBoardFragment.POST_READ_FRAGMENT, true, false, newBundle)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
            val rowPostListBinding = RowPostListBinding.inflate(layoutInflater)
            val allViewHolder = AllViewHolder(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return allViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
            holder.rowPostListSubject.text = "제목입니다. : $position"
            holder.rowPostListNickName.text = "작성자 : $position"
        }
    }


    // 검색 결과 게시글 목록을 보여주는 리사이클러 뷰의 어뎁터
    inner class ResultRecyclerViewAdapter : RecyclerView.Adapter<ResultRecyclerViewAdapter.ResultViewHolder>(){
        inner class ResultViewHolder(rowPostListBinding: RowPostListBinding) : RecyclerView.ViewHolder(rowPostListBinding.root){

            val rowPostListSubject:TextView
            val rowPostListNickName:TextView

            init{
                rowPostListSubject = rowPostListBinding.rowPostListSubject
                rowPostListNickName = rowPostListBinding.rowPostListNickName
                val newBundle = Bundle()
                newBundle.putString("title", "제목입니다 : $adapterPosition")
                newBundle.putString("contents", "작성자 : $adapterPosition")
                newBundle.putParcelable("mainFragment", mainFragment)
                mainFragment.replaceFragment(MainBoardFragment.POST_READ_FRAGMENT, true, false, newBundle)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
            val rowPostListBinding = RowPostListBinding.inflate(layoutInflater)
            val resultViewHolder = ResultViewHolder(rowPostListBinding)

            rowPostListBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return resultViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
            holder.rowPostListSubject.text = "제목입니다 : $position"
            holder.rowPostListNickName.text = "작성자 : $position"
        }
    }



}
package com.test.android73_miniproject3.Category_Fragment

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android73_miniproject3.DATA.Category
import com.test.android73_miniproject3.DB.DAOCategory
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity
import com.test.android73_miniproject3.R
import com.test.android73_miniproject3.databinding.CategoryEditDailogBinding
import com.test.android73_miniproject3.databinding.CategoryRycRowBinding
import com.test.android73_miniproject3.databinding.FragmentCategoryListBinding
import java.text.SimpleDateFormat
import kotlin.concurrent.thread


class CategoryListFragment : Fragment() {

    lateinit var fragmentCategoryListBinding: FragmentCategoryListBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentCategoryListBinding = FragmentCategoryListBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentCategoryListBinding.run{
            toolbarCategoryList.run{
                title = "카테고리 목록"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.category_add_menu)

                setOnMenuItemClickListener {

                    val cusdiaBinding = CategoryEditDailogBinding.inflate(layoutInflater)

                    cusdiaBinding.run {
                        toolbarAddDialog.title = "카테고리 추가"
                        toolbarAddDialog.setTitleTextColor(Color.WHITE)

                        addCategoryName.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                                cusdiaBinding.addCategoryNameLayout.error = null

                                if (mainActivity.categoryList.count { it.name == s.toString() } > 0) {
                                    cusdiaBinding.addCategoryNameLayout.error = "이미 존재하는 카테고리 입니다."
                                }
                                if (s.toString().isEmpty()) {
                                    cusdiaBinding.addCategoryNameLayout.error = "한 글자 이상 입력해주세요."
                                }
                            }

                            override fun beforeTextChanged(
                                s: CharSequence?,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {
                            }

                            override fun afterTextChanged(s: Editable?) {}
                        })


                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setView(cusdiaBinding.root)
                        builder.setNegativeButton("취소", null)
                        builder.setPositiveButton("추가") { dialogInterface: DialogInterface, i: Int ->

                            if(cusdiaBinding.addCategoryName.editableText.isNullOrBlank()){
                                Toast.makeText(mainActivity,"이름없는 카테고리는 저장할 수 없습니다. 이름을 설정하여 다시 시도해주세요",Toast.LENGTH_SHORT).show()
                                return@setPositiveButton
                            }else if (mainActivity.categoryList.count { it.name == cusdiaBinding.addCategoryName.editableText.toString() } > 0 ||
                                cusdiaBinding.addCategoryName.editableText.isNullOrEmpty()) {
                                Toast.makeText(mainActivity,"중복된 카테고리는 저장할 수 없습니다. 다른 이름으로 설정해주세요",Toast.LENGTH_SHORT).show()
                                return@setPositiveButton
                            } else {
                                val name = cusdiaBinding.addCategoryName.editableText.toString()
                                val updateTime = System.currentTimeMillis()
                                DAOCategory.insert(mainActivity, Category(name, updateTime))
                                mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                                fragmentCategoryListBinding.categoryListRecycView.adapter?.notifyDataSetChanged()
                            }

                        }
                        builder.show()

                        false
                    }

                }
            }

            categoryListRecycView.run {
                adapter = categoryRecycAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }

        }

        return fragmentCategoryListBinding.root
    }

    inner class categoryRecycAdapter : RecyclerView.Adapter<categoryRecycAdapter.CategoryViewHolder>(){
        inner class CategoryViewHolder(categoryRycRowBinding: CategoryRycRowBinding) : ViewHolder(categoryRycRowBinding.root){
            var textViewCategoryName : TextView
            var textViewUpdateTime : TextView
            init{
                textViewCategoryName = categoryRycRowBinding.textViewCategoryName
                textViewUpdateTime = categoryRycRowBinding.textViewUpdateTime

                categoryRycRowBinding.root.setOnClickListener {
                    mainActivity.selectCategory = mainActivity.categoryList[adapterPosition].name
                    mainActivity.memoList = DAOMemo.selectAll(mainActivity, mainActivity.selectCategory)
                    mainActivity.replaceFragment(MainActivity.FRAGMENT_MEMO_LIST, true, true)
                }

                categoryRycRowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    mainActivity.menuInflater.inflate(R.menu.categorylist_menu, menu)

                    menu[0].setOnMenuItemClickListener {
                        val cusdiaBinding = CategoryEditDailogBinding.inflate(layoutInflater)

                        cusdiaBinding.run {
                            toolbarAddDialog.title = "카테고리 변경"
                            toolbarAddDialog.setTitleTextColor(Color.WHITE)

                            addCategoryName.addTextChangedListener(object : TextWatcher {
                                override fun onTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                    cusdiaBinding.addCategoryNameLayout.error = null

                                    if (mainActivity.categoryList.count { it.name == s.toString() } > 0) {
                                        cusdiaBinding.addCategoryNameLayout.error = "이미 존재하는 카테고리 입니다."
                                    }
                                    if (s.toString().isEmpty()) {
                                        cusdiaBinding.addCategoryNameLayout.error = "한 글자 이상 입력해주세요."
                                    }

                                }

                                override fun beforeTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    count: Int,
                                    after: Int
                                ) {
                                }

                                override fun afterTextChanged(s: Editable?) {}
                            })

                            val builder = AlertDialog.Builder(mainActivity)
                            builder.setView(cusdiaBinding.root)
                            builder.setNegativeButton("취소", null)
                            builder.setPositiveButton("변경") { dialogInterface: DialogInterface, i: Int ->
                                if (cusdiaBinding.addCategoryName.editableText.toString().length < 1) {
                                    Toast.makeText(mainActivity,"이름없는 카테고리는 저장할 수 없습니다. 이름을 설정하여 다시 시도해주세요",Toast.LENGTH_SHORT).show()
                                    return@setPositiveButton
                                } else if(mainActivity.categoryList.count { it.name == cusdiaBinding.addCategoryName.editableText.toString() } > 0){
                                    Toast.makeText(mainActivity,"중복된 카테고리는 저장할 수 없습니다. 다른 이름으로 설정해주세요",Toast.LENGTH_SHORT).show()
                                    return@setPositiveButton
                                } else {
                                    val name = cusdiaBinding.addCategoryName.editableText.toString()
                                    val updateTime = System.currentTimeMillis()
                                    val ori = mainActivity.categoryList[adapterPosition]
                                    DAOCategory.update(mainActivity,ori ,Category(name, updateTime))
                                    mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                                    fragmentCategoryListBinding.categoryListRecycView.adapter?.notifyDataSetChanged()
                                }
                            }
                            builder.show()
                            false
                        }
                        false
                    }

                    menu[1].setOnMenuItemClickListener {
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("삭제 메시지")
                        builder.setMessage("정말 삭제하시겠습니까?")
                        builder.setNegativeButton("취소",null)
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            mainActivity.selectCategory = mainActivity.categoryList[adapterPosition].name
                            DAOCategory.deleteCategory(mainActivity, mainActivity.selectCategory)
                            mainActivity.categoryList = DAOCategory.selectAll(mainActivity)
                            fragmentCategoryListBinding.categoryListRecycView.adapter?.notifyDataSetChanged()
                        }
                        builder.show()

                        false
                    }
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryRecycAdapter.CategoryViewHolder {
            var categoryRycRowBinding = CategoryRycRowBinding.inflate(layoutInflater)
            var categoryViewHolder = CategoryViewHolder(categoryRycRowBinding)

            categoryRycRowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return categoryViewHolder
        }

        override fun getItemCount(): Int {
            return mainActivity.categoryList.size
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.textViewCategoryName.text = mainActivity.categoryList[position].name
            val dataFormat5 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val updateTime = dataFormat5.format(mainActivity.categoryList[position].updateTime)
            holder.textViewUpdateTime.text = "갱신 시간 : $updateTime"
        }
    }

}
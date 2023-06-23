package com.test.android_memohomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memohomework.category_memo.Memo
import com.test.android_memohomework.category_memo.TotalData.Companion.categoryMap
import com.test.android_memohomework.databinding.ActivityMainBinding
import com.test.android_memohomework.databinding.CategorynameBinding


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    // 카테고리 추가 액티비티
    lateinit var categoryAddActivityLauncher: ActivityResultLauncher<Intent>
    // 카테고리 수정 액티비티
    lateinit var categoryEditActivityLauncher: ActivityResultLauncher<Intent>
    // 카테고리 클릭 후 카테고리 메모 확인 액티비티
    lateinit var showMemoListActivityLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

 //---------------------------------------------------------------------------------------------------------
        // 메인 액티비티에서의 실행 내용.
        activityMainBinding.run{
            mainRecyclerView.run{
                adapter = MainRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

//---------------------------------------------------------------------------------------------------------

        // 카테고리 메모 확인 액티비티 종료된 후
        val showMemoListMain = ActivityResultContracts.StartActivityForResult()
        showMemoListActivityLauncher = registerForActivityResult(showMemoListMain){
            activityMainBinding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }

//---------------------------------------------------------------------------------------------------------
        // 카테고리 이름 수정하는 액티비티 종료된 후
        val categoryEditMain = ActivityResultContracts.StartActivityForResult()
        categoryEditActivityLauncher = registerForActivityResult(categoryEditMain){
            if(it.resultCode == RESULT_OK){

                val new = it.data!!.getStringExtra("newCategory").toString()
                val ori = it.data!!.getStringExtra("oriCategory").toString()

                val newMap = LinkedHashMap<String, ArrayList<Memo>>()

                categoryMap.forEach {
                    if(it.key != ori){
                        newMap[it.key] = it.value
                    } else {
                        newMap[new] = it.value
                    }
                }

                categoryMap = newMap

                activityMainBinding.mainRecyclerView.adapter?.notifyDataSetChanged()

            }
        }

//---------------------------------------------------------------------------------------------------------
        // 카테고리 추가 기능
        val categoryAddMain = ActivityResultContracts.StartActivityForResult()
        // 카테고리 추가 액티비티가 종료되어 메인 액티비티로 돌아왔을 때 실행될 코드.
        categoryAddActivityLauncher = registerForActivityResult(categoryAddMain){
            if(it.resultCode == RESULT_OK){
                val newCategory =  it.data!!.getStringExtra("addCategory")
                categoryMap[newCategory!!] = ArrayList<Memo>()

                activityMainBinding.mainRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

//---------------------------------------------------------------------------------------------------------
    // 카테고리 추가로 가능 메뉴에 대한 기능
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.addCategoryMenu -> {
                val addCategoryIntent = Intent(this@MainActivity, CategoryAddActivity::class.java)
                categoryAddActivityLauncher.launch(addCategoryIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
//---------------------------------------------------------------------------------------------------------

    // 카테고리 목록을 관리하는 리사이클러 뷰
    inner class MainRecyclerAdapter() : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(categorynameBinding: CategorynameBinding) : RecyclerView.ViewHolder(categorynameBinding.root){
            var categoryName : TextView
            init{
                categoryName = categorynameBinding.categoryName

                // 해당 아이템을 길게 클릭하였을 때 동작하는 기능
                categorynameBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->

                    menuInflater.inflate(R.menu.category_edit_del, menu)

                    menu[0].setOnMenuItemClickListener {
                        // 수정 이벤트 처리 기능
                        val categoryEditIntent = Intent(this@MainActivity, CategoryEditActivity::class.java)

                        val oriCategoryName = categoryMap.keys.toList()[adapterPosition]
                        categoryEditIntent.putExtra("oriName", oriCategoryName)

                        categoryEditActivityLauncher.launch(categoryEditIntent)

                        false
                    }

                    menu[1].setOnMenuItemClickListener {
                        // 삭제 이벤트 처리 기능
                        val removeCategoryName = categoryMap.keys.toList()[adapterPosition]
                        categoryMap.remove(removeCategoryName)

                        this@MainRecyclerAdapter.notifyDataSetChanged()

                        false
                    }
                }

                // 카테고리를 클릭하였을 때 이벤트 기능
                categorynameBinding.root.setOnClickListener {

                    val showMemoIntent = Intent(this@MainActivity,ShowMemoListActivity::class.java)
                    // 해당 카테고리의 이름을 전달해준다.
                    val categoryName = categoryMap.keys.toList()[adapterPosition]
                    showMemoIntent.putExtra("category",categoryName)

//                    showMemoIntent.putExtra("memoCount",categoryMap[categoryName]!!.size)
//
//                    for(i in 0 .. categoryMap[categoryName]!!.size-1){
//                        showMemoIntent.putExtra("title$i",categoryMap[categoryName]!![i].title)
//                        showMemoIntent.putExtra("contents$i",categoryMap[categoryName]!![i].contents)
//                    }

                    showMemoListActivityLauncher.launch(showMemoIntent)

                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val categoryAddBinding = CategorynameBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(categoryAddBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            categoryAddBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return categoryMap.keys.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.categoryName.text = categoryMap.keys.toList()[position]

        }
    }
//---------------------------------------------------------------------------------------------------------


}
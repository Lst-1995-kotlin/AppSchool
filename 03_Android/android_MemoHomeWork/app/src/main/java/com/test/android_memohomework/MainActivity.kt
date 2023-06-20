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
import com.test.android_memohomework.databinding.ActivityMainBinding
import com.test.android_memohomework.databinding.CategorynameBinding


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var categoryAddActivityLauncher: ActivityResultLauncher<Intent>

    lateinit var categoryEditActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

//---------------------------------------------------------------------------------------------------------
        // 카테고리 이름 수정하는 액티비티 종료된 후
        val categoryEditMain = ActivityResultContracts.StartActivityForResult()
        categoryEditActivityLauncher = registerForActivityResult(categoryEditMain){

        }

//---------------------------------------------------------------------------------------------------------
        // 카테고리 추가 기능
        val categoryAddMain = ActivityResultContracts.StartActivityForResult()
        // 카테고리 추가 액티비티가 종료되어 메인액티비티로 돌아왔을 때 실행될 코드.
        categoryAddActivityLauncher = registerForActivityResult(categoryAddMain){
            if(it.resultCode == RESULT_OK){
                val newCategory = it.data?.getStringExtra("categoryNewName")
                if(newCategory != null){
                    categoryMap[newCategory!!] = mutableListOf<Memo>()
                    categoryList = categoryMap.keys.toMutableList()
                    Log.d("카테고리","$newCategory")
                    val adapter = activityMainBinding.mainRecyclerView.adapter as CategoryRecyclerAdapter
                    adapter.notifyDataSetChanged()
                }
            }

        }
//---------------------------------------------------------------------------------------------------------

        activityMainBinding.run{
            mainRecyclerView.run{
                adapter = CategoryRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
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
    inner class CategoryRecyclerAdapter() : RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(categorynameBinding: CategorynameBinding) : RecyclerView.ViewHolder(categorynameBinding.root){
            var categoryName : TextView
            init{
                categoryName = categorynameBinding.categoryName

                val categoryName = categoryList[adapterPosition]
                // 카테고리 수정 및 삭제 기능
                categorynameBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->

                    menuInflater.inflate(R.menu.category_edit_del, menu)

                    menu[0].setOnMenuItemClickListener {
                        // 수정하는 이벤트 처리
                        val EditCategoryIntent = Intent(this@MainActivity, CategoryEditActivity::class.java)
                        EditCategoryIntent.putExtra("ori")
                        categoryEditActivityLauncher.launch(EditCategoryIntent)
                        false
                    }
                    menu[1].setOnMenuItemClickListener {
                        // 삭제하는 이벤트 처리
                        categoryMap.remove(categoryName)
                        categoryList = categoryMap.keys.toMutableList()
                        this@CategoryRecyclerAdapter.notifyDataSetChanged()

                        false
                    }
                }

                // 카테고리를 클릭하였을 때 이벤트
                categorynameBinding.root.setOnClickListener {

                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val categoryAddBinding = CategorynameBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(categoryAddBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )

            categoryAddBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return categoryList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.categoryName.text = categoryList[position]

        }
    }
//---------------------------------------------------------------------------------------------------------


}
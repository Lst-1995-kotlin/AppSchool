package com.test.android58_actionviewstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android58_actionviewstudy.databinding.ActivityMainBinding
import com.test.android58_actionviewstudy.databinding.RycRowBinding
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var foodList = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        repeat(10){
            foodList.add("소$it")
            foodList.add("돼지$it")
            foodList.add("닭$it")
            foodList.add("오리$it")
        }

        activityMainBinding.run {
            mainRycView.run{
                val firAdapter = RecyclerAdapter()
                firAdapter.filter(foodList)
                adapter = firAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

                addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val item1 = menu?.findItem(R.id.item1)

        val searchView = item1?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간 발생하는 이벤트

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어가 변경될 때 발생하는 이벤트
                if(newText != null){
                    var updateList = foodList.filter { it.contains(newText) } as MutableList<String>
                    val newadapter = RecyclerAdapter()
                    newadapter.filter(updateList)
                    activityMainBinding.mainRycView.adapter = newadapter
                }
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){

        var filterList = mutableListOf<String>()

        inner class ViewHolderClass(rowBinding: RycRowBinding): ViewHolder(rowBinding.root){
            var food : TextView
            init{
                food = rowBinding.textView

                rowBinding.root.setOnClickListener {
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("선택한 재료")
                    builder.setMessage("${filterList[adapterPosition]}을 선택하셨습니다.")
                    builder.setPositiveButton("확인", null)

                    builder.show()
                }

            }
        }

        fun filter(filter : MutableList<String>){
            filterList = filter
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RycRowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return filterList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.food.text = filterList[position]
        }
    }

}
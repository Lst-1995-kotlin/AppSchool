package com.test.android44_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android44_ex01.databinding.ActivityMainBinding
import com.test.android44_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
//    val temp = arrayOf("임시","임시2","임시3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            recyclerViewMain.run{
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.main_menu_add ->{
                val addIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(addIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {

            var textViewRow : TextView
            init{
                textViewRow = rowBinding.textView

                rowBinding.root.setOnClickListener {
                    val showIntent = Intent(this@MainActivity, ShowActivity::class.java)
                    startActivity(showIntent)
                }

            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return DataClass.fluitList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow.text = DataClass.fluitList[position].type
        }

    }

    override fun onResume() {
        super.onResume()

        val adapter = activityMainBinding.recyclerViewMain.adapter as RecyclerViewAdapter
        adapter.notifyDataSetChanged()
    }

}
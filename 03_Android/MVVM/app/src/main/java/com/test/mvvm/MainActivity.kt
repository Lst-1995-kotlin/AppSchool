package com.test.mvvm

import android.content.Intent
import android.inputmethodservice.Keyboard.Row
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.test.mvvm.databinding.ActivityMainBinding
import com.test.mvvm.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            buttonMain.setOnClickListener {
                val newIntent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(newIntent)
            }

            recyclerViewMain.run{
                adapter = MainRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                addItemDecoration(MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL))
            }
        }
    }

    inner class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {
        inner class MainViewHolder(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRow: TextView

            init {
                textViewRow = rowBinding.textViewRow

                rowBinding.root.setOnClickListener {
                    val newIntent = Intent(this@MainActivity, ResultActivity::class.java)
                    startActivity(newIntent)
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
            return 100
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textViewRow.text = "데이터입니다 : $position"
        }
    }
}
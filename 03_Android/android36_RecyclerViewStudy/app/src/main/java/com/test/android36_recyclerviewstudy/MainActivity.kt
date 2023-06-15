package com.test.android36_recyclerviewstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.android36_recyclerviewstudy.databinding.ActivityMainBinding
import com.test.android36_recyclerviewstudy.databinding.RowBinding
import org.w3c.dom.Text

data class Student(
    var name: String,
    var age: String,
    var korean: String
)

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val stdList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->

                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString()
                    val korean = editTextKorean.text.toString()
                    stdList.add(Student(name, age, korean))

                    val adapter = rv.adapter as RecyclerAdapterClass
                    adapter.notifyDataSetChanged()

                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")
                    clearFocus()

                    false
                }
            }

            rv.run{
                adapter = RecyclerAdapterClass()

                layoutManager =  GridLayoutManager(this@MainActivity,1)

            }
        }
    }

    inner class RecyclerAdapterClass: RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            var rowName : TextView
            var rowAge : TextView
            var rowKorean : TextView
            init {
                rowName = rowBinding.rowName
                rowAge = rowBinding.rowAge
                rowKorean = rowBinding.rowKorean
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                // 가로길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                // 세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return stdList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowName.text = "이름 : ${stdList[position].name}"
            holder.rowAge.text = "나이 : ${stdList[position].age}"
            holder.rowKorean.text = "국어점수 : ${stdList[position].korean}점"
        }
    }

}
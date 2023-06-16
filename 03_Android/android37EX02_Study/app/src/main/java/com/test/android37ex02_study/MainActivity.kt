package com.test.android37ex02_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android37ex02_study.databinding.ActivityMainBinding
import com.test.android37ex02_study.databinding.RowBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val dataList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run{

            thread {
                SystemClock.sleep(500)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)

            }

            editTextName.run{
                requestFocus()
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)
            }

            buttonAdd.run{
                setOnClickListener {
                    intputLayout.isVisible = true
                    recyclerView.isVisible = false

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(currentFocus,0)
                    editTextName.requestFocus()
                }
            }

            buttonLook.run {
                setOnClickListener {

                    if(currentFocus != null){
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                        clearFocus()
                    }



                    intputLayout.isVisible = false
                    recyclerView.isVisible = true
                }
            }

            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->

                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString()
                    val korean = editTextKorean.text.toString()

                    val newData = Student(name, age, korean)
                    dataList.add(newData)

                    val adapter = recyclerView.adapter as RecyclerAdapter

                    adapter.notifyDataSetChanged()

                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")

                    editTextName.requestFocus()

                    false
                }
            }

            recyclerView.run {
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)

            }

        }
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding):  RecyclerView.ViewHolder(rowBinding.root){
            var name : TextView
            var age : TextView
            var korean : TextView
            var removeBtn : Button
            init{
                name = rowBinding.textViewName
                age = rowBinding.textViewAge
                korean = rowBinding.textViewKorean
                removeBtn = rowBinding.buttonRemove
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
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            holder.run{
                name.text = dataList[position].name
                age.text = dataList[position].age
                korean.text = dataList[position].korean

                removeBtn.setOnClickListener {
                    dataList.removeAt(position)

                    val adapter = activityMainBinding.recyclerView.adapter as RecyclerAdapter
                    adapter.notifyDataSetChanged()

                }
            }

        }

    }

    data class Student(
        var name: String,
        var age: String,
        var korean: String
    )

}
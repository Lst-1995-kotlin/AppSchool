package com.test.android34_homework0614

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android34_homework0614.databinding.ActivityMainBinding
import com.test.android34_homework0614.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding


    val dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            listViewMain.run{
                adapter = CustomAdapter()
            }

            editTextMain.run{
                setOnEditorActionListener { v, actionId, event ->
                    dataList.add(text.toString())

                    setText("")

                    val adapter = listViewMain.adapter as CustomAdapter
                    adapter.notifyDataSetChanged()

                    false
                }
            }

        }

    }

    inner class CustomAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return dataList.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var rowBinding : RowBinding
            var rowView = convertView

            if(convertView == null){
                rowBinding = RowBinding.inflate(layoutInflater)
                rowView = rowBinding.root

                rowView.tag = rowBinding
            } else {
                rowBinding = rowView!!.tag as RowBinding
            }

            rowBinding.run {

                textViewRow.run{
                    text = dataList[position]
                }

                buttonRow.run{
                    setOnClickListener {
                        dataList.removeAt(position)

                        val adapter = activityMainBinding.listViewMain.adapter as CustomAdapter
                        adapter.notifyDataSetChanged()

                    }
                }
            }

            return rowView
        }

    }


}
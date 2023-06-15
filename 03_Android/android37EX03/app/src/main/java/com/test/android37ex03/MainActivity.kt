package com.test.android37ex03

import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android37ex03.databinding.ActivityMainBinding
import com.test.android37ex03.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val nationNameList = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )
    val nationImgList = arrayOf(
        R.drawable.imgflag1,
        R.drawable.imgflag2,
        R.drawable.imgflag3,
        R.drawable.imgflag4,
        R.drawable.imgflag5,
        R.drawable.imgflag6,
        R.drawable.imgflag7,
        R.drawable.imgflag8,
    )
    val InfoList = ArrayList<Info>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 일단 둘 다 가린다.
            linearLayout.isVisible = false
            recyclerView.isVisible = false

            buttonAdd.run{
                setOnClickListener {
                    linearLayout.isVisible = true
                    recyclerView.isVisible = false
                }
            }

            buttonLook.run{
                setOnClickListener {
                    linearLayout.isVisible = false
                    recyclerView.isVisible = true
                }
            }

            spinner.run{
                val sa = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    nationNameList
                )
                sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = sa

            }


            buttonSave.run {
                setOnClickListener {

                    val position = when(radioGroup.checkedRadioButtonId){
                        radioButtonAttack.id ->{
                            "공격수"
                        }
                        radioButtonMid.id -> {
                            "미드필더"
                        }
                        radioButtonDenfense.id -> {
                            "수비수"
                        } else ->{
                            "골키퍼"
                        }
                    }

                    InfoList.add(
                        Info(nationImgList[spinner.selectedItemPosition],
                            editTextName.text.toString(),
                            position
                        )
                    )

                    val adapter = recyclerView.adapter as RecyclerAdapterClass
                    adapter.notifyDataSetChanged()

                    editTextName.setText("")
                    radioGroup.clearCheck()
                    if(currentFocus != null){
                        currentFocus!!.clearFocus()
                    }
                }
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager =  LinearLayoutManager(this@MainActivity)
            }

        }

    }

    data class Info(
        val img:Int,
        val name: String,
        val position: String
    )

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var imageView2 = rowBinding.imageView2
            var textViewName = rowBinding.textViewName
            var textViewPosition = rowBinding.textViewPosition
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return InfoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.run{
                imageView2.setImageResource(InfoList[position].img)
                textViewName.text = "이름 : ${InfoList[position].name}"
                textViewPosition.text = "포지션 : ${InfoList[position].position}"
            }
        }

    }

}


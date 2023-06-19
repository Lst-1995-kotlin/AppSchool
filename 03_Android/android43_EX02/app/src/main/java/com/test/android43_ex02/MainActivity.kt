package com.test.android43_ex02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android43_ex02.databinding.ActivityMainBinding
import com.test.android43_ex02.databinding.RowBinding


data class fruit(val name: String, val count: String, val from: String)
val fruitList = mutableListOf<fruit>()



class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>

    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val c2 = ActivityResultContracts.StartActivityForResult()
        activityResultLauncher = registerForActivityResult(c2){

        }

        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1){
            if(it.resultCode == RESULT_OK){
                // add_fruit 액티비티에서 전달 받은 과일 정보 입력.
                val kindnum = it.data!!.getIntExtra("kind",0)
                val kind = when(kindnum){
                    0 -> "수박"
                    1 -> "사과"
                    2 -> "귤"
                    else -> ""
                }
                val count = it.data!!.getStringExtra("count")
                val from = it.data!!.getStringExtra("from")

                val newFruit = fruit(kind, count.toString(), from.toString())
                fruitList.add(newFruit)

                val adapter = activityMainBinding.RecyclerViewResult.adapter as RecyclerAdapter
                adapter.notifyDataSetChanged()

            }
        }

        activityMainBinding.run {
            RecyclerViewResult.run {
                adapter = RecyclerAdapter()

                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.main_MenuAdd -> {
                val addIntent = Intent(this@MainActivity, AddFruitActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var kind : TextView
            init{
                kind = rowBinding.textViewName

                rowBinding.root.run{
                    setOnClickListener {
                        val kind = fruitList[adapterPosition].name
                        val count = fruitList[adapterPosition].count
                        val from = fruitList[adapterPosition].from

                        val printIntent = Intent(this@MainActivity,ResultPrintActivity::class.java)
                        printIntent.putExtra("kind", kind)
                        printIntent.putExtra("count", count)
                        printIntent.putExtra("from", from)

                        activityResultLauncher.launch(printIntent)

                    }
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
            return fruitList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            holder.kind.text = fruitList[position].name

        }


    }


}
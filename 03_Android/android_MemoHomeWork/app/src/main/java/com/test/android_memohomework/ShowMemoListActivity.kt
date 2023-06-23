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
import com.test.android_memohomework.databinding.ActivityShowMemoBinding
import com.test.android_memohomework.databinding.ActivityShowMemoListBinding
import com.test.android_memohomework.databinding.MemoitemBinding


class ShowMemoListActivity : AppCompatActivity() {

    lateinit var activityShowMemoListBinding: ActivityShowMemoListBinding

    lateinit var addMemoActivityLauncher : ActivityResultLauncher<Intent>

    lateinit var editMemoActivityLauncher : ActivityResultLauncher<Intent>


    lateinit var categoryName : String

    var memoList = ArrayList<Memo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowMemoListBinding = ActivityShowMemoListBinding.inflate(layoutInflater)
        setContentView(activityShowMemoListBinding.root)
//---------------------------------------------------------------------------------------------------------

        activityShowMemoListBinding.run{

            ShowMemoRecycler.run{
                adapter = MemoRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@ShowMemoListActivity)
            }

            categoryName = intent.getStringExtra("category")!! // 전달받은 카테고리의 이름

//            var memoCount = intent.getIntExtra("memoCount",0) // 전달받은 카테고리의 저장되어 있는 메모
//            for(i in 0.. memoCount-1){
//                val memo = Memo()
//                memo.title = intent.getStringExtra("title$i")!!
//                memo.contents = intent.getStringExtra("contents$i")!!
//                memoList.add(memo)
//            }

            memoList = categoryMap[categoryName]!!
            val firAdapter = ShowMemoRecycler.adapter as MemoRecyclerAdapter
            firAdapter.notifyDataSetChanged()
            nowCategory.text = "$categoryName 메모리스트"


        }

//---------------------------------------------------------------------------------------------------------
        // 메모 수정 액티비티 종료 후
        val memoEditBack = ActivityResultContracts.StartActivityForResult()
        editMemoActivityLauncher = registerForActivityResult(memoEditBack) {
           activityShowMemoListBinding.ShowMemoRecycler.adapter?.notifyDataSetChanged()
        }

//---------------------------------------------------------------------------------------------------------
        // 메모 추가 액티비티 종료 후
        val memoAddBack = ActivityResultContracts.StartActivityForResult()
        addMemoActivityLauncher = registerForActivityResult(memoAddBack){
            if(it.resultCode == RESULT_OK){
                if(it.data != null){
                    val title = it.data?.getStringExtra("title")
                    val contents = it.data?.getStringExtra("contents")
                    val newMemo = Memo()
                    newMemo.title = title!!
                    newMemo.contents = contents!!
                    memoList.add(newMemo)
                    categoryMap[categoryName] = memoList
                    //Log.d("멋사"," 메모 추가 액티비티 종료 후 메모 리스트 확인 : ${memoList.toMutableList()}")
                }
            }
            activityShowMemoListBinding.ShowMemoRecycler.adapter?.notifyDataSetChanged()
        }

    }

//---------------------------------------------------------------------------------------------------------
    // 상단 메뉴 옵션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.memo_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.addMemo){
            val addMemoIntent = Intent(this@ShowMemoListActivity, MemoAddActivity::class.java)
            addMemoActivityLauncher.launch(addMemoIntent)
        }

        return super.onOptionsItemSelected(item)
    }

//---------------------------------------------------------------------------------------------------------
    // 메모의 제목과 내용을 보여주는 리사이클러 뷰
    inner class MemoRecyclerAdapter : RecyclerView.Adapter<MemoRecyclerAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(memoitemBinding: MemoitemBinding) :RecyclerView.ViewHolder(memoitemBinding.root){

            var title : TextView
            var contents : TextView

            init{
                title = memoitemBinding.textViewTitle
                contents = memoitemBinding.textViewContents

                // 메모 클릭 시 발생 이벤트
                memoitemBinding.root.setOnClickListener {

                    val showIntent = Intent(this@ShowMemoListActivity, ShowMemoActivity::class.java)
                    showIntent.putExtra("title", memoList[adapterPosition].title)
                    showIntent.putExtra("contents", memoList[adapterPosition].contents)
                    startActivity(showIntent)

                }

                memoitemBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->

                    menuInflater.inflate(R.menu.memo_edit_del,menu)


                    // 삭제하는 이벤트
                    menu[0].setOnMenuItemClickListener {
                        memoList.removeAt(adapterPosition)

                        this@MemoRecyclerAdapter.notifyDataSetChanged()

                        false
                    }
                    // 수정하는 이벤트
                    menu[1].setOnMenuItemClickListener {

                        val editMemoIntent = Intent(this@ShowMemoListActivity, MemoEditActivity::class.java)
                        editMemoIntent.putExtra("category",categoryName)
                        editMemoIntent.putExtra("editIndex", adapterPosition)
                        editMemoActivityLauncher.launch(editMemoIntent)
                        false
                    }

                }

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val memoitemBinding = MemoitemBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(memoitemBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            memoitemBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.title.text = memoList[position].title
            holder.contents.text = memoList[position].contents
        }

    }

}
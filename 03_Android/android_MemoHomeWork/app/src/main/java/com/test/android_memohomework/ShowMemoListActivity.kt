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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memohomework.category_memo.Memo
import com.test.android_memohomework.databinding.ActivityShowMemoBinding
import com.test.android_memohomework.databinding.ActivityShowMemoListBinding


class ShowMemoListActivity : AppCompatActivity() {

    lateinit var activityShowMemoListBinding: ActivityShowMemoListBinding

    lateinit var addMemoActivityLauncher : ActivityResultLauncher<Intent>

    lateinit var categoryName : String

    var memoList = ArrayList<Memo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowMemoListBinding = ActivityShowMemoListBinding.inflate(layoutInflater)
        setContentView(activityShowMemoListBinding.root)

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

                    Log.d("멋사"," 메모 추가 액티비티 종료 후 메모 리스트 확인 : ${memoList}")
                }
            }
            val addAdapter = activityShowMemoListBinding.ShowMemoRecycler.adapter as MemoRecyclerAdapter
            addAdapter.notifyDataSetChanged()
        }


//---------------------------------------------------------------------------------------------------------

        activityShowMemoListBinding.run{

            ShowMemoRecycler.run{
                adapter = MemoRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@ShowMemoListActivity)
            }

            categoryName = intent.getStringExtra("category")!! // 전달받은 카테고리의 이름

            var memoCount = intent.getIntExtra("memoCount",0) // 전달받은 카테고리의 저장되어 있는 메모
            for(i in 1.. memoCount){
                val memo = Memo()
                memo.title = intent.getStringExtra("title")!!
                memo.contents = intent.getStringExtra("contents")!!
                memoList.add(memo)
            }
            val firAdapter = ShowMemoRecycler.adapter as MemoRecyclerAdapter
            firAdapter.notifyDataSetChanged()
            nowCategory.text = "$categoryName 메모리스트"


        }
    }

//---------------------------------------------------------------------------------------------------------
    // 해당 액티비티가 중지 될 때 현재의 memoList의 저장된 Memo를 전달한다.
    override fun onPause() {

        val resultIntent = Intent()
        resultIntent.putExtra("category",categoryName)
        resultIntent.putExtra("memoCount",memoList.size)

        Log.d("멋사"," onPause 메모 리스트 확인 : ${memoList}")

        if(!memoList.isEmpty()){
            for(i in 0 .. memoList.size-1) {
                resultIntent.putExtra("title$i",memoList[i].title)
                resultIntent.putExtra("contents$i",memoList[i].contents)
            }
        }

        setResult(RESULT_OK, resultIntent)

        super.onPause()
    }
//---------------------------------------------------------------------------------------------------------
     //해당 액티비티가 다시 시작하면
//    override fun onResume() {
//
//        activityShowMemoListBinding.run{
//            categoryName = intent.getStringExtra("category").toString() // 전달받은 카테고리의 이름
//
//            val memoCount = intent.getIntExtra("memoCount",0) // 전달받은 카테고리의 저장되어 있는 메모
//            for(i in 1.. memoCount){
//                val memo = Memo()
//                memo.title = categoryName
//                memo.contents = intent.getStringExtra("contents")!!
//                memoList.add(memo)
//            }
//            val firAdapter = ShowMemoRecycler.adapter as MemoRecyclerAdapter
//            firAdapter.notifyDataSetChanged()
//            nowCategory.text = "$categoryName 메모리스트"
//
//        }
//
//        super.onResume()
//    }


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
        inner class ViewHolderClass(showMemoBinding: ActivityShowMemoBinding) :RecyclerView.ViewHolder(showMemoBinding.root){

            var title : TextView
            var contents : TextView

            init{
                title = showMemoBinding.textViewMemoTitle
                contents = showMemoBinding.textViewMemoContents
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val showMemoBinding = ActivityShowMemoBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(showMemoBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            showMemoBinding.root.layoutParams = params

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
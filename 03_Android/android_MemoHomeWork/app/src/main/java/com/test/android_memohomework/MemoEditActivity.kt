package com.test.android_memohomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memohomework.R
import com.test.android_memohomework.category_memo.Memo
import com.test.android_memohomework.category_memo.TotalData.Companion.categoryMap
import com.test.android_memohomework.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {

    lateinit var memoEditBinding: ActivityMemoEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memoEditBinding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(memoEditBinding.root)

        memoEditBinding.run {

            val catName = intent.getStringExtra("category")

            val memoList = categoryMap[catName]
            val editIndex = intent.getIntExtra("editIndex",0)
            TextViewEditMemoTitle.text = "수정 메모명 : ${memoList?.get(editIndex)?.title}"

            buttonMemoEditCancle.setOnClickListener {
                finish()
            }
            buttonMemoEdit.setOnClickListener {

                val newMemo = Memo()
                newMemo.title = if(editTextMemoTilte.text.toString() != "") editTextMemoTilte.text.toString() else memoList?.get(editIndex)?.title.toString()
                newMemo.contents = editTextMemoContent.text.toString()
                memoList?.set(editIndex, newMemo)

                setResult(RESULT_OK)
                finish()
            }

        }

    }
}
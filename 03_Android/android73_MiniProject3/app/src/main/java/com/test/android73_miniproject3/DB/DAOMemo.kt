package com.test.android73_miniproject3.DB

import android.content.Context
import com.test.android73_miniproject3.DATA.Category
import com.test.android73_miniproject3.DATA.Memo

class DAOMemo {

    companion object{

        val colums = arrayOf("category","memoTitle","memoContents", "updateTime",)

        fun insert(context: Context, memo: Memo){
            val sql = """insert into Memo
                |(category, memoTitle, memoContents, updateTime)
                |values(?, ?, ?, ?)
            """.trimMargin()

            val args = arrayOf(memo.category, memo.memoTitle, memo.memoContents, memo.updateTime)

            val dbHelperMemo = DBHelperMemo(context)
            dbHelperMemo.writableDatabase.execSQL(sql, args)

            dbHelperMemo.close()
        }

        // 키를 통하여 해당 정보만 업데이트
        fun update(context: Context, ori: Memo, memo: Memo){
            delete(context, ori)
            insert(context, memo)

        }


        fun selectAll(context: Context, category: String): MutableList<Memo>{
            val memoList = mutableListOf<Memo>()

            val sql ="""select * from Memo
                |where category = ?
                |order by updateTime DESC
            """.trimMargin()
            val arg = arrayOf(category)
            val dbHelperMemo = DBHelperMemo(context)
            val cursor = dbHelperMemo.writableDatabase.rawQuery(sql, arg)

            while(cursor.moveToNext()){
                val position = IntArray(colums.size)
                for(i in 0 until position.size){
                    position[i] = cursor.getColumnIndex(colums[i])
                }
                val category = cursor.getString(position[0])
                val memoTitle = cursor.getString(position[1])
                val memoContents = cursor.getString(position[2])
                val updateTime = cursor.getLong(position[3])
                memoList.add(Memo(category, memoTitle, memoContents, updateTime))
            }

            return memoList
        }

        fun delete(context: Context, memo: Memo){
            val sql = """delete from Memo
                |where updateTime = ?
            """.trimMargin()
            val arg = arrayOf(memo.updateTime)
            val dbHelperMemo = DBHelperMemo(context)
            dbHelperMemo.writableDatabase.execSQL(sql, arg)
            dbHelperMemo.close()
        }

        fun deleteAllMemo(context: Context, category: String){
            val sql = """delete from Memo
              |where category = ?
          """.trimMargin()
            val arg = arrayOf(category)

            val dbHelperMemo = DBHelperMemo(context)
            dbHelperMemo.writableDatabase.execSQL(sql, arg)
            dbHelperMemo.close()

        }

    }
}
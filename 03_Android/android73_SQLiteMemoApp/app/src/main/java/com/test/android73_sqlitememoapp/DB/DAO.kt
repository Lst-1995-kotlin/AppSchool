package com.test.android73_sqlitememoapp.DB

import android.content.Context
import com.test.android73_sqlitememoapp.Memo

class DAO {

    companion object{

        val colums = arrayOf("time", "date", "title", "contents")

        fun insert(context: Context, memo: Memo){
            val sql = """insert into Memo
                |(time, date, title, contents)
                |values(?, ?, ?, ?)
            """.trimMargin()
            val args = arrayOf(
                memo.key, memo.date, memo.title, memo.contents
            )
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // 키를 통하여 해당 정보만 업데이트
        fun update(context: Context, primaryKey: Long, memo: Memo){
            val sql = """update Memo
                |set date = ?, title = ?, contents = ?
                |where time = ?
            """.trimMargin()
            val args = arrayOf(
                memo.date, memo.title, memo.contents, primaryKey
            )
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun selectAll(context: Context) : MutableList<Memo>{
            val sql = "select * from Memo"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            val memoList = mutableListOf<Memo>()

            while(cursor.moveToNext()){
                val position = IntArray(colums.size)

                for(i in 0 .. colums.size-1){
                    position[i] = cursor.getColumnIndex(colums[i])
                }

                val time = cursor.getLong(position[0])
                val date = cursor.getString(position[1])
                val title = cursor.getString(position[2])
                val contents = cursor.getString(position[3])

                val memo = Memo(time, date, title, contents)
                memoList.add(memo)
            }

            dbHelper.close()

            return memoList
        }

        fun select(context: Context, selKey : Long): Memo{
            val sql = """select * from Memo
                | where time = ?
            """.trimMargin()

            val dbHelper = DBHelper(context)
            val arg = arrayOf(selKey.toString())
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg)
            cursor.moveToFirst()

            val position = IntArray(colums.size)
            for(i in 0 .. colums.size-1){
                position[i] = cursor.getColumnIndex(colums[i])
            }
            val key = cursor.getLong(position[0])
            val date = cursor.getString(position[1])
            val title = cursor.getString(position[2])
            val contents = cursor.getString(position[3])

            val memo = Memo(key, date, title, contents)
            dbHelper.close()

            return memo
        }

        fun deleteMemo(context: Context, selKey: Long){
            val sql = "delete from Memo where time = ?"
            val arg = arrayOf(selKey)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, arg)
            dbHelper.close()

        }

    }

}
package com.test.android73_miniproject3.DB

import android.content.Context
import com.test.android73_miniproject3.DATA.Category

class DAOCategory {

    companion object{

        val colums = arrayOf("category", "updateTime",)

        fun insert(context: Context, categroy: Category){
            val sql = """insert into Category
                |(category, updateTime)
                |values(?, ?)
            """.trimMargin()

            val args = arrayOf(categroy.name, categroy.updateTime)

            val dbHelperCategory = DBHelperCategory(context)
            dbHelperCategory.writableDatabase.execSQL(sql, args)

            dbHelperCategory.close()
        }

        fun updateTime(context: Context, name: String, time: Long){
            val sql = """update Category
                |set updateTime = ?
                |where category = ?
            """.trimMargin()
            val arg = arrayOf(time, name)

            val dbHelperCategory = DBHelperCategory(context)
            dbHelperCategory.writableDatabase.execSQL(sql, arg)

        }

        // 키를 통하여 해당 정보만 업데이트
        fun update(context: Context, ori: Category, categroy: Category){

            // 기존 카테고리 내 메모 가져오기
            val memoList = DAOMemo.selectAll(context, ori.name)
            DAOMemo.deleteAllMemo(context, ori.name)
            deleteCategory(context, ori.name)

            val sql = """insert into Category
                |(category, updateTime)
                |values(?, ?)
            """.trimMargin()

            val arg = arrayOf(
                categroy.name, categroy.updateTime
            )
            val dbHelperCategory = DBHelperCategory(context)
            dbHelperCategory.writableDatabase.execSQL(sql, arg)
            dbHelperCategory.close()
            for(memo in memoList){
                DAOMemo.insert(context, memo)
            }
        }

        fun selectAll(context: Context): MutableList<Category>{
            val categroyList = mutableListOf<Category>()

            val sql = """select * from Category
                |order by updateTime DESC
            """.trimMargin()
            val dbHelperCategory = DBHelperCategory(context)
            val cursor = dbHelperCategory.writableDatabase.rawQuery(sql, null)

            while(cursor.moveToNext()){
                val categroyIdx = cursor.getColumnIndex(colums[0])
                val updateTimeIdx = cursor.getColumnIndex(colums[1])

                val name = cursor.getString(categroyIdx)
                val updateTime = cursor.getLong(updateTimeIdx)

                val categroy = Category(name, updateTime)
                categroyList.add(categroy)
            }
            dbHelperCategory.close()
            return categroyList
        }


        fun deleteCategory(context: Context, categryName: String){
            val sql = """delete from Category
                |where category = ?
            """.trimMargin()
            val arg = arrayOf(categryName)

            val dbHelperCategory = DBHelperCategory(context)
            dbHelperCategory.writableDatabase.execSQL(sql, arg)
            dbHelperCategory.close()

            DAOMemo.deleteAllMemo(context, categryName)

        }

    }

}
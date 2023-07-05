package com.test.android72_sqlitestudy0705

import android.content.Context
import android.util.Log

class DAO {
    companion object{

        fun insertData(context: Context, data : Student){
            val sql = """insert into Student
                |(name, age, korean)
                |values(?,?,?)
            """.trimMargin()

            val arg = arrayOf(
                data.name, data.age, data.korean
            )

            val sqliteDataBase = DBHelper(context)
            sqliteDataBase.writableDatabase.execSQL(sql, arg)
            sqliteDataBase.close()

        }

        fun selectData(){

        }

        fun selectAllData(context: Context) : MutableList<Student>{
            val stdList = mutableListOf<Student>()

            val sql = "select * from Student"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            Log.d("멋사", "$cursor")
            val colums = arrayOf("name", "age", "korean")

            while (cursor.moveToNext()){
                val positions = IntArray(colums.size)
                for(i in 0 .. colums.size-1){
                    positions[i] = cursor.getColumnIndex(colums[i])
                }
                val name = cursor.getString(positions[0])
                val age = cursor.getInt(positions[1])
                val korean = cursor.getInt(positions[2])
                val newStd = Student(name, age, korean)
                stdList.add(newStd)
            }

            dbHelper.close()

            return stdList
        }

        fun removeData(){

        }

    }
}
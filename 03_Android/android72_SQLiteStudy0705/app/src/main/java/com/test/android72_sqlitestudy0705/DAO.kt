package com.test.android72_sqlitestudy0705

import android.content.Context
import android.util.Log

class DAO {
    companion object{

        fun insertData(context: Context, data : Student){
            val sql = """insert into Student
                |(timeKey, name, age, korean)
                |values(?, ?, ?, ?)
            """.trimMargin()

            val arg = arrayOf(
                data.timeKey, data.name, data.age, data.korean
            )

            val sqliteDataBase = DBHelper(context)
            sqliteDataBase.writableDatabase.execSQL(sql, arg)
            sqliteDataBase.close()

        }


        fun selectData(context: Context, timeKey: Long): Student {
            val sql = "select * from Student where timeKey = $timeKey"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            val colums = arrayOf("timeKey", "name", "age", "korean")
            val positions = IntArray(colums.size)

            cursor.moveToFirst()

            for (i in 0..colums.size-1) {
                positions[i] = cursor.getColumnIndex(colums[i])
            }

            val key = cursor.getLong(positions[0])
            val name = cursor.getString(positions[1])
            val age = cursor.getInt(positions[2])
            val korean = cursor.getInt(positions[3])

            dbHelper.close()

            val selStd = Student(key, name, age, korean)


            return selStd
        }

        fun selectAllData(context: Context) : MutableList<Student>{
            val stdList = mutableListOf<Student>()

            val sql = "select * from Student"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            val colums = arrayOf("timeKey", "name", "age", "korean")

            while (cursor.moveToNext()){
                val positions = IntArray(colums.size)
                for(i in 0 .. colums.size-1){
                    positions[i] = cursor.getColumnIndex(colums[i])
                }
                val timeKey = cursor.getLong(positions[0])
                val name = cursor.getString(positions[1])
                val age = cursor.getInt(positions[2])
                val korean = cursor.getInt(positions[3])
                val newStd = Student(timeKey,name, age, korean)
                stdList.add(newStd)
            }

            dbHelper.close()

            return stdList
        }

        fun removeData(context: Context, delKey : Long){
            val sql = "delete from Student where timeKey = ?"
            val arg = arrayOf(
                delKey
            )
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, arg)
            dbHelper.close()
        }

    }
}
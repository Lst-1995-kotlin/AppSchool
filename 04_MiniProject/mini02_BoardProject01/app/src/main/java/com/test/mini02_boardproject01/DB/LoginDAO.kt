package com.test.mini02_boardproject01.DB

import android.content.Context
import com.test.mini02_boardproject01.DATA.UserInfo
import java.lang.Exception

class LoginDAO {

    companion object{

        val colums = arrayOf("id", "pw", "nickName", "age", "hobby")

        fun insert(context: Context, userInfo: UserInfo){
            val sql = """insert into Login
                |(id, pw, nickName, age, hobby)
                |values(?, ?, ?, ?, ?)
            """.trimMargin()
            val args = arrayOf(
                userInfo.id, userInfo.pw, userInfo.nickName, userInfo.age, userInfo.hobby
            )
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun LoginCheck(context: Context, id: String, pw: String): UserInfo?{
            val sql = """select * from Login
                | where id = ? and pw = ?
            """.trimMargin()

            val dbHelper = DBHelper(context)
            val arg = arrayOf(id, pw)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg)

            cursor.moveToFirst()
            val position = IntArray(colums.size)
            for(i in 0 .. colums.size-1){
                position[i] = cursor.getColumnIndex(colums[i])
            }

            try{
                val userId = cursor.getString(position[0])
                val userPw = cursor.getString(position[1])
                val nickName = cursor.getString(position[2])
                val age = cursor.getInt(position[3])
                val hobby = cursor.getString(position[4])

                val userInfo = UserInfo(userId, userPw, nickName, age, hobby)

                dbHelper.close()
                return userInfo

            } catch (e: Exception){
                return null
            }

        }

    }

}
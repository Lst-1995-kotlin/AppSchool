package com.test.android73_miniproject3.Auth

import android.content.Context
import java.lang.Exception

class DAOAuth {

    companion object{

        fun insert(context: Context, password: String){
            val sql = """insert into Login
                |(password)
                |values(?)
            """.trimMargin()

            var args = arrayOf(password)

            val dbHelperAuth = DBHelperAuth(context)
            dbHelperAuth.writableDatabase.execSQL(sql, args)
            dbHelperAuth.close()
        }

        fun passWordCheck(context: Context, password: String):Boolean{
            var check = true

            val sql = "select * from Login"
            val dbHelperAuth = DBHelperAuth(context)
            val cursor = dbHelperAuth.writableDatabase.rawQuery(sql, null)

            cursor.moveToFirst()
            var saveNum = ""
            try {
                saveNum = cursor.getString(0)
            } catch (e: Exception){
                check = false
            }
            dbHelperAuth.close()

            if(saveNum != password) check = false

            return check
        }

        fun contains(context: Context):Boolean{
            var check = true

            val sql = "select * from Login"
            val dbHelperAuth = DBHelperAuth(context)
            val cursor = dbHelperAuth.writableDatabase.rawQuery(sql, null)

            cursor.moveToFirst()
            try {
               cursor.getString(0)
            } catch (e: Exception){
                check = false
            }

            return check
        }


    }
}
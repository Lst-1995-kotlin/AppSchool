package com.test.android73_miniproject3.Auth

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperAuth (context: Context): SQLiteOpenHelper(context, "Auth.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table Login
            |(password text primary key)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
package com.test.mini02_boardproject01.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table Login
            |(id text primary key,
            |pw text not null,
            |nickName text not null,
            |age integer not null,
            |hobby text)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
package com.test.android73_sqlitememoapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "MempApp.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table Memo
            |(time long primary key,
            |date text not null,
            |title text not null,
            |contents text not null)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
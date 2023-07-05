package com.test.android72_sqlitestudy0705

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Std.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table Student
            |(timeKey long primary key not Null,
            | name text not Null,
            | age integer not Null,
            | korean integer not Null)
        """.trimMargin()

        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
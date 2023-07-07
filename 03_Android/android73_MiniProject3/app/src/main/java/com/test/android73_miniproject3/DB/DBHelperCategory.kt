package com.test.android73_miniproject3.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperCategory(context: Context): SQLiteOpenHelper(context, "Category.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table Category
            |(category text primary key,
            |updateTime long not null
            |)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
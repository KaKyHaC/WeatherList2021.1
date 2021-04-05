package com.dvbar.todolist2021

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, "TodoListDB", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(DbContract.CREATE_TABLE_SQL)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}
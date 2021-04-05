package com.dvbar.todolist2021

import android.provider.BaseColumns

object DbContract {

    object NotesEntry : BaseColumns {
        const val TABLE_NAME = "NotesTable"
        const val TEXT_COLUMN_NAME = "text_column"
    }

    const val CREATE_TABLE_SQL = "CREATE TABLE ${NotesEntry.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY, ${NotesEntry.TEXT_COLUMN_NAME} TEXT)"
}
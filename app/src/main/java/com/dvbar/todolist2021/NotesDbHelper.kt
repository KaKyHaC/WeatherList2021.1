package com.dvbar.todolist2021

import android.content.ContentValues
import android.content.Context

class NotesDbHelper(context: Context) {

    private val dbHelper: DbHelper = DbHelper(context)

    fun saveNote(note: String) {
        val writableDatabase = dbHelper.writableDatabase
        val value = ContentValues()
        value.put(DbContract.NotesEntry.TEXT_COLUMN_NAME, note)
        writableDatabase.insert(DbContract.NotesEntry.TABLE_NAME, null, value)
    }

    fun readNotes(): List<String> {
        val listNotes = mutableListOf<String>()
        val readableDatabase = dbHelper.readableDatabase
        val cursor = readableDatabase.query(
            DbContract.NotesEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val columnIndex = cursor.getColumnIndex(DbContract.NotesEntry.TEXT_COLUMN_NAME)
        while (cursor.moveToNext()) {
            val string = cursor.getString(columnIndex)
            listNotes.add(string)
        }
        return listNotes
    }

}
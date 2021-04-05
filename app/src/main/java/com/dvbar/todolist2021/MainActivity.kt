package com.dvbar.todolist2021

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recyclerAdapter = WeatherDataAdapter()
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.edit_text)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        dbHelper = DbHelper(this)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        editText.setText(readText())

        for (data in readNotes()) {
            recyclerAdapter.add(WeatherMapper.toWeatherData(data))
        }

        btn.setOnClickListener(this)
        btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val text = editText.text.toString()
            saveText(text)
            intent.putExtra("key", text)
            startActivityForResult(intent, 1)
        }
    }

    fun saveText(text: String) {
        val sharedPreferences = getSharedPreferences("Main", MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("editText", text)
            .commit()
    }

    fun readText(): String {
        val sharedPreferences = getSharedPreferences("Main", MODE_PRIVATE)
        return sharedPreferences.getString("editText", "").orEmpty()
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val stringExtra = data?.getStringExtra("newKey").orEmpty()
            saveNote(stringExtra)
            recyclerAdapter.add(WeatherMapper.toWeatherData(stringExtra))
        }
    }

    override fun onClick(p0: View?) {}
}
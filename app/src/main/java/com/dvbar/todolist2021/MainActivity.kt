package com.dvbar.todolist2021

import android.content.ContentValues
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvbar.todolist2021.WeatherMapper.parseWeatherData

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recyclerAdapter = WeatherDataAdapter()
    private lateinit var dbHelper: NotesDbHelper

    private val readyBroadcastReceiver = ReadyBroadcastReceiver(::updateAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.edit_text)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        dbHelper = NotesDbHelper(this)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        editText.setText(readText())

//        for (data in readNotes()) {
//            recyclerAdapter.add(WeatherMapper.toWeatherData(data))
//        }

        updateAdapter()

        btn.setOnClickListener(this)
        btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val text = editText.text.toString()
            saveText(text)
            intent.putExtra("key", text)
            startActivityForResult(intent, 1)
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(readyBroadcastReceiver, ReadyBroadcastReceiver.filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(readyBroadcastReceiver)
    }

    fun updateAdapter() {
        recyclerAdapter.clean()
        dbHelper.readNotes()
            .map { it.parseWeatherData() }
            .filter { it.name?.contains("i", true).isTrue() }
            .sortedBy { it.name }
            .reversed()
            .forEach { recyclerAdapter.add(it) }
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val stringExtra = data?.getStringExtra("newKey").orEmpty()
            dbHelper.saveNote(stringExtra)
            recyclerAdapter.add(WeatherMapper.toWeatherData(stringExtra))
        }
    }

    override fun onClick(p0: View?) {}
}
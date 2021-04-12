package com.dvbar.todolist2021

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val editText = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)

        val stringExtra = intent.getStringExtra("key")
        editText.text = stringExtra

        button.setOnClickListener {
            val text = editText.text.toString()
            WeatherService.run(this, text)
        }
    }
}
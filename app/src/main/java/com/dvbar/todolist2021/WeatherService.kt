package com.dvbar.todolist2021

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class WeatherService : Service() {

    lateinit var notesDbHelper: NotesDbHelper

    override fun onBind(p0: Intent?) = null

    override fun onCreate() {
        super.onCreate()
        notesDbHelper = NotesDbHelper(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra(CITY_EXTRA)?.let { city ->
            Thread {
                Thread.sleep(10000)
                val weather = WebApiHelper.getWeather(city)
                notesDbHelper.saveNote(weather)
                ReadyBroadcastReceiver.sendBroadcast(applicationContext)
                Log.d("SecondActivity", weather)
            }.start()
        }
        return START_NOT_STICKY
    }

    companion object {
        private const val CITY_EXTRA = "CITY_EXTRA"

        fun run(context: Context, city: String) {
            context.startService(Intent(context, WeatherService::class.java).apply {
                putExtra(CITY_EXTRA, city)
            })
        }
    }
}
package com.dvbar.todolist2021

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class ReadyBroadcastReceiver(val callback: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        callback()
    }

    companion object {
        private const val action = "ACTION_READY"

        val filter = IntentFilter(action)

        fun sendBroadcast(context: Context) {
            context.sendBroadcast(Intent(action))
        }
    }
}
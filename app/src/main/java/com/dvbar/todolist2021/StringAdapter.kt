package com.dvbar.todolist2021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StringAdapter : RecyclerView.Adapter<StringAdapter.StringViewHolder>(){

    private val data = mutableListOf<String>()

    fun add(text: String) {
        data.add(text)
        notifyDataSetChanged()
    }

    class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun update(text: String) {
            val textView = itemView.findViewById<TextView>(R.id.text_view)
            textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.weather_item_layout, parent, false)
        return  StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.update(data[position])
    }

    override fun getItemCount() = data.size
}
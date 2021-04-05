package com.dvbar.todolist2021

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val array: MutableList<WeatherData> = mutableListOf()

    fun add(string: WeatherData) {
        array.add(string)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.update(array[position])
    }

    override fun getItemCount(): Int {
        return array.size
    }

    class WeatherViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.string_item, parent, false)
    ) {

        private val textCity = itemView.findViewById<TextView>(R.id.text1)
        private val textTemp = itemView.findViewById<TextView>(R.id.temp_text)
        private val textPressure = itemView.findViewById<TextView>(R.id.pressure_text)

        fun update(data: WeatherData) {
            textCity.text = data.name
            textTemp.text = data.main?.temp.toString()
            textPressure.text = data.main?.pressure.toString()
        }
    }
}
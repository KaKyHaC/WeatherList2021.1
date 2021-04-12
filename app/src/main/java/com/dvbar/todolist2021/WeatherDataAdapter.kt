package com.dvbar.todolist2021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherDataAdapter : RecyclerView.Adapter<WeatherDataAdapter.WeatherDataViewHolder>(){

    private val data = mutableListOf<WeatherData>()

    fun clean() {
        data.clear()
        notifyDataSetChanged()
    }

    fun add(text: WeatherData) {
        data.add(text)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherDataViewHolder(parent, LayoutInflater.from(parent.context))

    override fun onBindViewHolder(holder: WeatherDataViewHolder, position: Int) {
        holder.update(data[position])
    }

    override fun getItemCount() = data.size

    class WeatherDataViewHolder(
        parent: ViewGroup, layoutInflater: LayoutInflater
    ) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.weather_item_layout, parent, false)) {

        private val textCity = itemView.findViewById<TextView>(R.id.text_view)
        private val textTemp = itemView.findViewById<TextView>(R.id.text_temp)
        private val textPressure = itemView.findViewById<TextView>(R.id.text_pressure)

        fun update(data: WeatherData) {
            textCity.text = data.name
            textTemp.text = data.main?.temp.toString()
            textPressure.text = data.main?.pressure.toString()
        }
    }
}
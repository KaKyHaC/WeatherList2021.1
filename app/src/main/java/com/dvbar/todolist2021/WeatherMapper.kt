package com.dvbar.todolist2021

import com.google.gson.Gson

object WeatherMapper {

    private val gson = Gson()

    fun toWeatherData(json: String) = gson.fromJson(json,  WeatherData::class.java)
}
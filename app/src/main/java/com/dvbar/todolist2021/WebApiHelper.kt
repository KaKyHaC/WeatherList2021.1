package com.dvbar.todolist2021

import java.io.InputStreamReader
import java.net.URL

object WebApiHelper {

    private const val API_KEY = "01576f9c003c7bd26e072f9c8db59683"

    fun getUrl(city: String) = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY"

    fun getWeather(city: String): String {
        val url = URL(getUrl(city))
        val openConnection = url.openConnection()
        val inputStream = openConnection.getInputStream()
        val inputStreamReader = InputStreamReader(inputStream)
        return inputStreamReader.readText()
    }
}
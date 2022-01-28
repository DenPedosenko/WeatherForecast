package com.preprod.weatherforecast.domain.model

data class DailyForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayWeather>,
    val message: Double
)
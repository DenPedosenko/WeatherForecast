package com.preprod.weatherforecast.domain.repository

import com.preprod.weatherforecast.data.DataOrException
import com.preprod.weatherforecast.domain.model.DailyForecast
import java.lang.Exception

interface WeatherRepository {
    suspend fun qetWeather(
        city: String,
        units: String
    ): DataOrException<DailyForecast, Exception>
}
package com.preprod.weatherforecast.data.repository

import com.preprod.weatherforecast.data.DataOrException
import com.preprod.weatherforecast.data.network.WeatherApi
import com.preprod.weatherforecast.domain.model.DailyForecast
import com.preprod.weatherforecast.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import java.lang.Exception

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    private val dataOrException = DataOrException<DailyForecast, Exception>()

    override suspend fun qetWeather(
        city: String,
        units: String
    ): DataOrException<DailyForecast, Exception> {
        try {
            dataOrException.data =
                api.getWeatherForecast(query = city, units = units)
        } catch (exception: Exception) {
            dataOrException.exception = exception
        }
        return dataOrException
    }
}
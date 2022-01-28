package com.preprod.weatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preprod.weatherforecast.data.DataOrException
import com.preprod.weatherforecast.domain.model.DailyForecast
import com.preprod.weatherforecast.domain.model.Units
import com.preprod.weatherforecast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    private val _isLoading = mutableStateOf(true)
    val isLoading:State<Boolean> = _isLoading

    private val _data: MutableState<DataOrException<DailyForecast, Exception>> =
        mutableStateOf(
            DataOrException(null, null)
        )
    val data: State<DataOrException<DailyForecast, Exception>> = _data

    init {
        getWeatherForecast("Kiev")
    }

    fun getWeatherForecast(city: String, units: Units = Units.metric) {
        viewModelScope.launch {
            _data.value = repository.qetWeather(city = city, units = units.name)
            if (_data.value.data.toString().isNotEmpty()) {
                _isLoading.value = false
            }
        }
    }
}
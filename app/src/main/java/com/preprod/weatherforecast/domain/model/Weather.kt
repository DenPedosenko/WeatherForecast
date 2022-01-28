package com.preprod.weatherforecast.domain.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
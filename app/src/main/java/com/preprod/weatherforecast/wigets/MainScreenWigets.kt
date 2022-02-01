package com.preprod.weatherforecast.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.preprod.weatherforecast.R
import com.preprod.weatherforecast.domain.model.DayWeather
import com.preprod.weatherforecast.utils.Constants
import com.preprod.weatherforecast.utils.Utils

@Composable
fun WeekForecast(weekForecast: List<DayWeather>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
            items(items = weekForecast) { item ->
                WeatherDetailRow(item)
            }
        }
    }

}

@Composable
fun WeatherDetailRow(dayWeather: DayWeather) {
    val weatherImageUrl = Constants.IMAGE_URL + "${dayWeather.weather[0].icon}.png"
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = Utils.formatDay(dayWeather.dt),
            fontWeight = FontWeight.Bold
        )
        WeatherStateImage(imageUrl = weatherImageUrl, modifier = Modifier.size(70.dp))
        Card(
            modifier = Modifier.padding(12.dp),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(0xFFFFC400)
        ) {
            Text(modifier = Modifier.padding(4.dp), text = dayWeather.weather[0].description)
        }
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(Utils.formatDecimals(dayWeather.temp.day) + "°")
                }
                append("/")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(Utils.formatDecimals(dayWeather.temp.night) + "°")
                }
            },
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun SunsetSunriseRow(weather: DayWeather) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconWithText(R.drawable.sunrise, Utils.formatDateTime(weather.sunrise))
        IconWithText(R.drawable.sunset, Utils.formatDateTime(weather.sunset))
    }
}

@Composable
fun HumidityWindPressureRow(weather: DayWeather) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconWithText(R.drawable.humidity, "${weather.humidity}%")
        IconWithText(R.drawable.pressure, "${weather.pressure} psi")
        IconWithText(R.drawable.wind, "${weather.speed} mph")
    }
}
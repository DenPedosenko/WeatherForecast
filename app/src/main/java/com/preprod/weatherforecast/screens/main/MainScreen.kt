package com.preprod.weatherforecast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.preprod.weatherforecast.components.HumidityWindPressureRow
import com.preprod.weatherforecast.components.SunsetSunriseRow
import com.preprod.weatherforecast.components.WeatherStateImage
import com.preprod.weatherforecast.components.WeekForecast
import com.preprod.weatherforecast.domain.model.DailyForecast
import com.preprod.weatherforecast.screens.main.MainViewModel
import com.preprod.weatherforecast.screens.navigation.WeatherScreens
import com.preprod.weatherforecast.utils.Constants
import com.preprod.weatherforecast.utils.Utils
import com.preprod.weatherforecast.wigets.WeatherAppBar

@Composable
fun MainScreen(navController: NavHostController, city: String?) {
    val viewModel: MainViewModel = hiltViewModel()
    if (city != null) {
        viewModel.getWeatherForecast(city = city)
    }
    if (viewModel.isLoading.value) {
        CircularProgressIndicator()
    } else {
        val weather = viewModel.data.value
        if (weather.data != null) {
            MainScaffold(weather.data!!, navController)
        }
    }
}

@Composable
fun MainScaffold(weather: DailyForecast, navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        WeatherAppBar(
            title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(route = WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp
        ) {

        }
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: DailyForecast) {
    val currentDayWeather = data.list[0]
    val imageUrl = Constants.IMAGE_URL + "${currentDayWeather.weather[0].icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Utils.formatDate(currentDayWeather.dt), style = MaterialTheme.typography.caption,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = Utils.formatDecimals(currentDayWeather.temp.day) + "??",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = currentDayWeather.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = currentDayWeather)
        Divider()
        SunsetSunriseRow(weather = currentDayWeather)
        Text(
            text = "This week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        WeekForecast(weekForecast = data.list.subList(1, data.list.size))
    }
}
package com.preprod.weatherforecast.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.preprod.weatherforecast.screens.MainScreen
import com.preprod.weatherforecast.screens.SplashScreen
import com.preprod.weatherforecast.screens.main.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController)
        }
        composable(WeatherScreens.MainScreen.name) {
            MainScreen(navController)
        }
    }
}
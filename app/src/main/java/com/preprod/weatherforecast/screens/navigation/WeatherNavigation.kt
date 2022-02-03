package com.preprod.weatherforecast.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preprod.weatherforecast.screens.MainScreen
import com.preprod.weatherforecast.screens.SplashScreen
import com.preprod.weatherforecast.screens.search.SearchScreen

@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val defaultCity: String
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                MainScreen(navController, city = city)
            }
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController)
        }
    }
}
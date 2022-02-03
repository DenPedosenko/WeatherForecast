package com.preprod.weatherforecast.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.preprod.weatherforecast.screens.navigation.WeatherScreens
import com.preprod.weatherforecast.wigets.CommonTextField
import com.preprod.weatherforecast.wigets.WeatherAppBar

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) {
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchForm(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    navController.navigate(route = WeatherScreens.MainScreen.name + "/${it}")
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value, searchQueryState.value.trim()::isNotEmpty)

    Column(modifier) {
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Enter the city",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}
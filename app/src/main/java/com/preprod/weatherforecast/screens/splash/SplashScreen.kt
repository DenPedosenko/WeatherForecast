package com.preprod.weatherforecast.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.preprod.weatherforecast.R
import com.preprod.weatherforecast.screens.main.MainViewModel
import com.preprod.weatherforecast.screens.navigation.WeatherScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(
            durationMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))
        delay(500L)
        navController.navigate(WeatherScreens.MainScreen.name)
    })

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(300.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Splash screen sunny icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(95.dp)
            )
            Text(
                text = "Find the Sun?",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )
        }
    }
}

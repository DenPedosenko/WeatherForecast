package com.preprod.weatherforecast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun WeatherStateImage(modifier: Modifier = Modifier, imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "weather icon",
        modifier = modifier.size(80.dp)
    )
}
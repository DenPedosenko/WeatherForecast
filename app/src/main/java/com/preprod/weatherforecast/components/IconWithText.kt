package com.preprod.weatherforecast.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconWithText(iconResource: Int, label: String) {
    Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = "labeled icon",
            modifier = Modifier
                .padding(2.dp)
                .size(25.dp),
        )
        Text(text = label, style = MaterialTheme.typography.caption)
    }
}
package com.preprod.weatherforecast.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEE, MMM dd")
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDateTime(timestamp: Int): String {
        val sdf = SimpleDateFormat("hh:mm:aa")
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun formatDecimals(item: Double): String {
        return "%.0f".format(item)
    }
}
package com.preprod.weatherforecast.data

import java.lang.Exception

class DataOrException<T, E : Exception>(
    var data: T? = null,
    var exception: E? = null
)
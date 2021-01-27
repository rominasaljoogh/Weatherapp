package com.example.weatherapp.data.entity.forecastweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded

@Keep
data class FutureWeatherEnt(
    @Embedded( prefix = "city_")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<ResponseGetForecastWeather>,
    @SerializedName("message")
    val message: Double
)
package com.example.weatherapp.data.entity.forecastweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded

@Keep
data class ResponseGetForecastWeather (
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("snow")
    val snow: Double,
    @SerializedName("speed")
    val speed: Double,
    @Embedded( prefix = "temp_")
    val temp: Temp,
    @SerializedName("weather")
    val weather: List<Weather>
)
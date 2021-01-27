package com.example.weatherapp.data.entity.FutureWetherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseGetFutureWeather(
    @SerializedName("current")
    val current: Current,
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("minutely")
    val minutely: List<Minutely>,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)
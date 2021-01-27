package com.example.weatherapp.data.entity.onecall


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded

@Keep
data class ResponseGetWeather(
    @Embedded( prefix = "current_")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
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
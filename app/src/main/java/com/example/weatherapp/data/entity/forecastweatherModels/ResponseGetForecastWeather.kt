package com.example.weatherapp.data.entity.forecastweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.entity.currentweatherModels.CURRENT_WEATHER_ID

const val Forecast_WEATHER_ID = 0
@Keep
@Entity ( tableName = "ForecastWeather")
data class ResponseGetForecastWeather(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int,
    @Embedded( prefix = "coord_")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @Embedded( prefix = "main_")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @Embedded( prefix = "sys_")
    val sys: Sys,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) {
    @PrimaryKey(autoGenerate = false)
    var idForecastWeather : Int = Forecast_WEATHER_ID
}
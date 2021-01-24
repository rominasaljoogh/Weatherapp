package com.example.weatherapp.data.entity.currentweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


const val CURRENT_WEATHER_ID = 0

@Keep
@Entity ( tableName = "CurrentWeather")
data class ResponseGetCurrentWeather(
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
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    @TypeConverters(CurrentWeatherListConverter::class)
    val weather: List<Weather>,
    @Embedded( prefix = "wind_")
    val wind: Wind
) {
    @PrimaryKey(autoGenerate = false)
    var idCurrentWeather : Int = CURRENT_WEATHER_ID
}
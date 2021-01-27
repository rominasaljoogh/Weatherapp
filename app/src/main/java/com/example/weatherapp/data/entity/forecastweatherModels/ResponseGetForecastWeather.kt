package com.example.weatherapp.data.entity.forecastweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.data.entity.currentweatherModels.CURRENT_WEATHER_ID
import com.example.weatherapp.data.entity.currentweatherModels.CurrentWeatherListConverter
import com.example.weatherapp.data.entity.currentweatherModels.Weather

const val Future_WEATHER_ID = 0

@Keep
@Entity( tableName = "FutureWeather")
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
    @TypeConverters(FutureWeatherListConverter::class)
    val weather: List<Weather>,
) {
    @PrimaryKey(autoGenerate = false)
    var idFutureWeather : Int = Future_WEATHER_ID
}
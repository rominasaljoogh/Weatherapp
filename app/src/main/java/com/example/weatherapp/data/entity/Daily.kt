package com.example.weatherapp.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val FUTURE_WEATHER_ID = 0

@Entity(tableName = "future_weather")
data class Daily(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Int,
    @Embedded( prefix = "feelsLike_")
    val feelsLike: FeelsLike,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @Embedded( prefix = "temp_")
    val temp: Temp,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = FUTURE_WEATHER_ID
}
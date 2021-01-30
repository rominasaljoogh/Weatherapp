package com.example.weatherapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

const val HOURLY_WEATHER_ID = 0

@Entity(tableName = "hourly_weather")
data class Hourly(
    @SerializedName("clouds")
    val clouds: Double,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @PrimaryKey(autoGenerate = false)
    var id: Int = HOURLY_WEATHER_ID
)
package com.example.weatherapp.data.entity.currentweatherModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
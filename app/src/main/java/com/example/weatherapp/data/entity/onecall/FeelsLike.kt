package com.example.weatherapp.data.entity.onecall


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class FeelsLike(
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("night")
    val night: Double
)
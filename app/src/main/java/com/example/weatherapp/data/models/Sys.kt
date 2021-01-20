package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("countrycountry")
    var country: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("message")
    var message: Double? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("sunset")
    var sunset: Int? = null,
    @SerializedName("type")
    var type: Int? = null
)
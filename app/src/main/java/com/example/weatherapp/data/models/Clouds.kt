package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    var all: Int? = null
)
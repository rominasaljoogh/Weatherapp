package com.example.weatherapp.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Weather(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null
)
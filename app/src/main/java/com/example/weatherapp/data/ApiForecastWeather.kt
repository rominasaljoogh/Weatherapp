package com.example.weatherapp.data

import com.example.weatherapp.data.entity.onecall.ResponseGetWeather
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiForecastWeather {
    @POST("/data/2.5/onecall")
    fun getForecastWeather(
            @Query("lat") lat : String,
            @Query("lon") lon : String,
            @Query("appid") appId : String,
            @Query("units") units : String
    ): Call<ResponseGetWeather>
}
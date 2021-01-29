package com.example.weatherapp.data.network

import com.example.weatherapp.data.entity.ResponseGetWeather
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiWeather {
    @POST("/data/2.5/onecall")
    fun getWeather(
            @Query("lat") lat : String,
            @Query("lon") lon : String,
            @Query("appid") appId : String,
            @Query("units") units : String
    ): Call<ResponseGetWeather>
}
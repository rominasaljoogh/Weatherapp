package com.example.weatherapp.data

import com.example.weatherapp.data.entity.currentweatherModels.ResponseGetCurrentWeather
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiCurrentWeather {

        @POST("/data/2.5/weather")
        fun getCurrentWeather(
                @Query("q") cityName : String,
                @Query("appid") appId : String,
                @Query("units") units : String
        ): Call<ResponseGetCurrentWeather>
}
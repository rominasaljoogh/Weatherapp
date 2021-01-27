package com.example.weatherapp.data

import com.example.weatherapp.data.entity.forecastweatherModels.FutureWeatherEntity
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query




interface ApiForecastWeather {
    @POST("/data/2.5/forecast/daily")
    fun getForecastWeather(
            @Query("q") cityName : String,
            @Query("appid") appId : String,
            @Query("cnt") cnt: Int,
            @Query("units") units : String
    ): Call<ResponseGetForecastWeather>
}
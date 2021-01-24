package com.example.weatherapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitWeatherInstance{

    var INSTANCE: Retrofit? = null

    fun getRetrofitInstance(): Retrofit {

        if (INSTANCE == null) {

            INSTANCE = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return INSTANCE!!
    }
}
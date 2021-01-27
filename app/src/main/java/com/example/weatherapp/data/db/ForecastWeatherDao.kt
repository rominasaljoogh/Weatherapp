package com.example.weatherapp.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.weatherapp.data.entity.forecastweatherModels.FutureWeatherEntity
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather


interface ForecastWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: ResponseGetForecastWeather)
}
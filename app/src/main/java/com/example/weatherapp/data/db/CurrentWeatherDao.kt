package com.example.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.entity.currentweatherModels.CURRENT_WEATHER_ID
import com.example.weatherapp.data.entity.currentweatherModels.ResponseGetCurrentWeather

interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: ResponseGetCurrentWeather)
}
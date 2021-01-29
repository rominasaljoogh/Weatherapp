package com.example.weatherapp.data.db

import androidx.room.*
import com.example.weatherapp.data.entity.CURRENT_WEATHER_ID
import com.example.weatherapp.data.entity.Current

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFutureWeather(weatherEntry: Current)

    @Delete
    fun deleteAll(weatherEntry: Current)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getAllFutureData(): Current
}
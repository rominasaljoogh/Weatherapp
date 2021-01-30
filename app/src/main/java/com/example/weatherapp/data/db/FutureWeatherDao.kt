package com.example.weatherapp.data.db

import androidx.room.*
import com.example.weatherapp.data.entity.Daily
import com.example.weatherapp.data.entity.FUTURE_WEATHER_ID

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFutureWeather(weatherEntry: List<Daily>)

    @Delete
    fun deleteAllF(weatherEntry: List<Daily>)

    @Query("select * from future_weather where id = $FUTURE_WEATHER_ID")
    fun getAllFutureData(): List<Daily>
}
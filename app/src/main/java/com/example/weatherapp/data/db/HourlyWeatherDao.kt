package com.example.weatherapp.data.db

import androidx.room.*
import com.example.weatherapp.data.entity.HOURLY_WEATHER_ID
import com.example.weatherapp.data.entity.Hourly

@Dao
interface HourlyWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourWeather(weatherEntry: List<Hourly>)

    @Delete
    fun deleteAllH(weatherEntry: List<Hourly>)

    @Query("select * from hourly_weather where id = $HOURLY_WEATHER_ID")
    fun getAllHourData(): List<Hourly>
}
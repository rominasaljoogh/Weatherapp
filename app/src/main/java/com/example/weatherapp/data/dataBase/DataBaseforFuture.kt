package com.example.weatherapp.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.db.FutureWeatherDao
import com.example.weatherapp.data.db.HourlyWeatherDao
import com.example.weatherapp.data.entity.Daily
import com.example.weatherapp.data.entity.Hourly
import com.example.weatherapp.data.entity.WeatherListConverter

@Database(
        entities = [Daily::class, Hourly::class] , version = 1)

@TypeConverters(WeatherListConverter::class)
abstract class DataBaseforFuture : RoomDatabase() {

    abstract fun getFutureWeatherDao() : FutureWeatherDao
    abstract fun getHourlyWeatherDao() : HourlyWeatherDao

    companion object {

        var INSTANCE : DataBaseforFuture? = null

        fun getDataBaseforFutureInstance(context: Context) : DataBaseforFuture? {
            if(INSTANCE == null){
                synchronized(DataBaseforFuture::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            DataBaseforFuture::class.java,
                            "weatherFEntriesDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}
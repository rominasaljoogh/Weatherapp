package com.example.weatherapp.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.db.ForecastWeatherDao
import com.example.weatherapp.data.entity.currentweatherModels.CurrentWeatherListConverter
import com.example.weatherapp.data.entity.currentweatherModels.ResponseGetCurrentWeather
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather


@Database(
        entities = [ResponseGetCurrentWeather ::class ,
                    ResponseGetForecastWeather ::class] , version = 1)
@TypeConverters(CurrentWeatherListConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun getCurrentWeatherDao() : CurrentWeatherDao
    abstract fun getForecastWeatherDao() : ForecastWeatherDao

    companion object {

        var INSTANCE : DataBase? = null

        fun getDataBaseInstance(context: Context) : DataBase? {
            if(INSTANCE == null){
                synchronized(DataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                                    DataBase::class.java, "weatherEntriesDB")
                                                    .allowMainThreadQueries()
                                                    .build()
                }
            }
            return INSTANCE
        }
    }
}
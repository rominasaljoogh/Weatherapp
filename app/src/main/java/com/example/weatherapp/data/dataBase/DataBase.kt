package com.example.weatherapp.data.dataBase

import android.content.Context
import androidx.room.*
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.db.FutureWeatherDao
import com.example.weatherapp.data.entity.Current
import com.example.weatherapp.data.entity.Daily
import com.example.weatherapp.data.entity.WeatherListConverter

@Database(
        entities = [Current ::class] , version = 1)

@TypeConverters(WeatherListConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun getCurrentWeatherDao() : CurrentWeatherDao
    //abstract fun getFutureWeatherDao() : FutureWeatherDao

    companion object {

        var INSTANCE : DataBase? = null

        fun getDataBaseInstance(context: Context) : DataBase? {
            if(INSTANCE == null){
                synchronized(DataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                                    DataBase::class.java,
                                                    "weatherEntriesDB")
                                                    .allowMainThreadQueries()
                                                    .build()
                }
            }
            return INSTANCE
        }
    }
}
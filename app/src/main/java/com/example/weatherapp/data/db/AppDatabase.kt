package com.example.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.models.ResponseGetCurrentWeather

@Database(entities = [ResponseGetCurrentWeather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao() : CurrentWeatherDao

    companion object {

        var INSTANCE: AppDatabase? = null

        fun getNoteDataBaseInstance(context: Context): AppDatabase? {

            if (INSTANCE == null) {

                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "noteDB").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

    }
}
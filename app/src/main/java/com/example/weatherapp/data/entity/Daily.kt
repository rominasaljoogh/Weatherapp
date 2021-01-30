package com.example.weatherapp.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val FUTURE_WEATHER_ID = 0

@Entity(tableName = "future_weather")
//@Parcelize
data class Daily(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Int,
    @Embedded( prefix = "feelsLike_")
    val feelsLike: FeelsLike,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @Embedded( prefix = "temp_")
    val temp: Temp,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @PrimaryKey(autoGenerate = false)
    var id: Int = FUTURE_WEATHER_ID
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        TODO("feelsLike"),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("temp"),
        parcel.readDouble(),
        TODO("weather"),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(clouds)
        parcel.writeDouble(dewPoint)
        parcel.writeInt(dt)
        parcel.writeInt(humidity)
        parcel.writeDouble(pop)
        parcel.writeInt(pressure)
        parcel.writeDouble(rain)
        parcel.writeInt(sunrise)
        parcel.writeInt(sunset)
        parcel.writeDouble(uvi)
        parcel.writeInt(windDeg)
        parcel.writeDouble(windSpeed)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Daily> {
        override fun createFromParcel(parcel: Parcel): Daily {
            return Daily(parcel)
        }

        override fun newArray(size: Int): Array<Daily?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.weatherapp.ui.weather.future

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.ResponseGetWeather
import kotlinx.android.synthetic.main.fragment_forecast7_day.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyItemRecyclerViewAdapter(
    private val responseData: ResponseGetWeather
//    , private val onclick : (ResponseGetFutureWeather) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_forecast7_day, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView_condition.text = responseData.daily[position].weather[0].description

        val dateF : SimpleDateFormat = SimpleDateFormat("E d/M")
        holder.textView_date.text = dateF.format((responseData.daily[position].dt)*1000)

        holder.textView_temperature.text = responseData.daily[position].temp.day.toString()
        Glide.with(holder.itemView.context)
                .load("http://openweathermap.org/img/wn/${responseData.daily[position].weather[0].icon}@2x.png")
                .into(holder.imageView_condition_icon)

//        holder.itemView.setOnClickListener{
//            onclick(data[position])
//          }
        }

    override fun getItemCount(): Int = responseData.daily.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textView_condition: TextView = view.textView_condition
        var textView_date: TextView = view.textView_date
        var textView_temperature: TextView = view.textView_temperature
        var imageView_condition_icon = view.imageView_condition_icon
    }
}
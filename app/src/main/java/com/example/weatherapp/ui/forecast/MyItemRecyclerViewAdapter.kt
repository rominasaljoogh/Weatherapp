package com.example.weatherapp.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.FutureWetherModels.ResponseGetFutureWeather
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_forecast7_day.view.*

class MyItemRecyclerViewAdapter(
    private val responseData: ResponseGetFutureWeather
//    , private val onclick : (ResponseGetFutureWeather) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_forecast7_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView_condition.text = responseData.weather?.get(0)?.main
        holder.textView_date.text = responseData.dt.toString()
        holder.textView_temperature.text = responseData.temp.day.toString()
        Glide.with(holder.itemView.context)
                .load("http://openweathermap.org/img/wn/${responseData.weather?.get(0)?.icon}@2x.png")
                .into(holder.imageView_condition_icon)

//        holder.itemView.setOnClickListener{
//            onclick(data[position])
//          }
        }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textView_condition: TextView = view.textView_condition
        var textView_date: TextView = view.textView_date
        var textView_temperature: TextView = view.textView_temperature
        var imageView_condition_icon = view.imageView_condition_icon
    }
}
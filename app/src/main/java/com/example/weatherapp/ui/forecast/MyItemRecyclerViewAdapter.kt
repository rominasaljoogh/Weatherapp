package com.example.weatherapp.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastweatherModels.FutureWeatherEntity
import kotlinx.android.synthetic.main.fragment_forecast7_day.view.*

class MyItemRecyclerViewAdapter(
    private val data: FutureWeatherEntity
//    , private val onclick : (ResponseGetForecastWeather) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_forecast7_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView_condition.text = ""
        holder.textView_date.text = ""
        holder.textView_temperature.text = ""
        Glide.with(holder.itemView.context)
                .load("http:")
                .into(holder.imageView_condition_icon)
//        holder.itemView.setOnClickListener{
//            onclick(data[position])
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